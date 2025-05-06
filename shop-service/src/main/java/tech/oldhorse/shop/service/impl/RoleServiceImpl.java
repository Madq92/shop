package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.entity.RoleDO;
import tech.oldhorse.shop.dao.entity.RoleResourceDO;
import tech.oldhorse.shop.dao.repository.RoleRepository;
import tech.oldhorse.shop.dao.repository.RoleResourceRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.object.model.ResourceModel;
import tech.oldhorse.shop.service.object.model.RoleModel;
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleCoreConvert roleCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;
    @Autowired
    RoleResourceRepository roleResourceRepository;

    @Autowired
    ResourceService resourceService;

    @Override
    public List<RoleModel> listByCondition(RoleCondition condition) {
        List<RoleDO> list = roleRepository.lambdaQuery()
                .eq(RoleDO::getDeletedFlag, false)
                .in(CollectionUtils.isNotEmpty(condition.getRoleIds()), RoleDO::getRoleId, condition.getRoleIds())
                .list();
        return roleCoreConvert.doList2ModelList(list);
    }

    @Override
    public RoleModel getByRoleId(String roleId) {
        RoleDO one = roleRepository.lambdaQuery().eq(RoleDO::getDeletedFlag, false).eq(RoleDO::getRoleId, roleId).one();
        AssertUtils.notNull(one);
        return roleCoreConvert.do2Model(one);
    }

    @Override
    public String create(RoleModel roleModel) {
        String roleId = idGenerator.nextStringId();
        roleModel.setRoleId(roleId);

        RoleDO roleDO = roleCoreConvert.model2Do(roleModel);
        roleRepository.save(roleDO);
        return roleId;
    }

    @Override
    public Boolean edit(RoleModel roleModel) {
        RoleModel roleInDb = getByRoleId(roleModel.getRoleId());
        AssertUtils.notNull(roleInDb);

        RoleDO roleDO = roleCoreConvert.model2Do(roleModel);
        roleDO.setId(roleInDb.getId());
        return roleRepository.updateById(roleDO);
    }

    @Override
    public Boolean delete(String roleId) {
        RoleModel roleInDb = getByRoleId(roleId);
        AssertUtils.notNull(roleInDb);

        RoleResourceDO one = roleResourceRepository.lambdaQuery().eq(RoleResourceDO::getRoleId, roleId).one();
        AssertUtils.isNull(one, "角色被引用，无法删除");

        RoleDO update = new RoleDO();
        update.setId(roleInDb.getId());
        update.setDeletedFlag(true);
        return roleRepository.updateById(update);
    }

    @Override
    public Boolean addResource(String roleId, RoleAddResourceReq req) {
        RoleModel roleInDb = getByRoleId(roleId);
        AssertUtils.notNull(roleInDb, "角色不存在");

        ResourceCondition resourceCondition = new ResourceCondition();
        resourceCondition.setResourceIds(req.getResourceIds());
        List<ResourceModel> resourceModels = resourceService.listByCondition(resourceCondition);
        AssertUtils.notNull(resourceModels, "资源不存在");

        List<RoleResourceDO> list = resourceModels.stream().map(resourceModel -> new RoleResourceDO(roleId, resourceModel.getResourceId())).toList();
        return roleResourceRepository.saveBatch(list);
    }

    @Override
    public Boolean delResource(String roleId, RoleDelResourceReq req) {
        List<RoleResourceDO> list = roleResourceRepository.lambdaQuery().eq(RoleResourceDO::getRoleId, roleId).in(RoleResourceDO::getResourceId, req.getResourceIds()).list();
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return roleResourceRepository.removeBatchByIds(list.stream().map(RoleResourceDO::getId).toList());
    }
}
