package tech.oldhorse.shop.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.entity.RoleDO;
import tech.oldhorse.shop.dao.entity.RoleResourceDO;
import tech.oldhorse.shop.dao.entity.UserRoleDO;
import tech.oldhorse.shop.dao.repository.RoleRepository;
import tech.oldhorse.shop.dao.repository.RoleResourceRepository;
import tech.oldhorse.shop.dao.repository.UserRoleRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.object.model.ResourceModel;
import tech.oldhorse.shop.service.object.model.RoleModel;

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
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<RoleModel> listByCondition(RoleCondition condition) {
        List<RoleDO> list = roleRepository.lambdaQuery()
                .eq(RoleDO::getDeletedFlag, false)
                .in(CollectionUtils.isNotEmpty(condition.getRoleIds()), RoleDO::getRoleId, condition.getRoleIds())
                .orderByAsc(RoleDO::getSort)
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
    public RoleModel getDetail(String roleId) {
        RoleModel roleModel = getByRoleId(roleId);

        // 获取资源
        ResourceCondition condition = new ResourceCondition();
        condition.setRoleIds(Lists.newArrayList(roleId));
        List<ResourceModel> resourceModels = resourceService.listByCondition(condition);
        roleModel.setResources(resourceModels);

        return roleModel;
    }

    @Override
    public String create(RoleModel roleModel) {
        String roleId = idGenerator.nextStringId();
        roleModel.setRoleId(roleId);

        if (roleModel.getSort() == null) {
            roleModel.setSort(getMaxSort() + 1);
        }
        roleRepository.save(roleCoreConvert.model2Do(roleModel));

        // 创建资源关联
        List<ResourceModel> resources = roleModel.getResources();
        if (CollectionUtils.isNotEmpty(resources)) {
            List<RoleResourceDO> roleResourceDOList = resources.stream().map(resourceModel -> new RoleResourceDO(roleId, resourceModel.getResourceId())).toList();
            roleResourceRepository.saveBatch(roleResourceDOList);
        }
        return roleId;
    }

    @Override
    public Boolean edit(RoleModel roleModel) {
        RoleModel roleInDb = getByRoleId(roleModel.getRoleId());
        AssertUtils.notNull(roleInDb);

        if (roleModel.getSort() == null) {
            roleModel.setSort(getMaxSort() + 1);
        }

        RoleDO roleDO = roleCoreConvert.model2Do(roleModel);
        roleDO.setId(roleInDb.getId());
        roleRepository.updateById(roleDO);

        // 创建资源关联
        String roleId = roleModel.getRoleId();
        // -- 全删
        roleResourceRepository.lambdaUpdate().eq(RoleResourceDO::getRoleId, roleId).remove();
        // -- 全增
        if (CollectionUtils.isNotEmpty(roleModel.getResources())) {
            List<RoleResourceDO> list = roleModel.getResources().stream().map(resourceModel -> new RoleResourceDO(roleId, resourceModel.getResourceId())).toList();
            roleResourceRepository.saveBatch(list);
        }
        return true;
    }

    private Integer getMaxSort() {
        return roleRepository.lambdaQuery()
                .eq(RoleDO::getDeletedFlag, false)
                .orderByDesc(RoleDO::getSort)
                .last("limit 1")
                .oneOpt()
                .map(RoleDO::getSort)
                .orElse(0);
    }

    @Override
    public Boolean delete(String roleId) {
        RoleModel roleInDb = getByRoleId(roleId);
        AssertUtils.notNull(roleInDb);

        UserRoleDO one = userRoleRepository.lambdaQuery().eq(UserRoleDO::getRoleId, roleId).last("limit 1").one();
        AssertUtils.isNull(one, "角色被用户引用，无法删除");

        RoleDO update = new RoleDO();
        update.setId(roleInDb.getId());
        update.setDeletedFlag(true);
        roleRepository.updateById(update);

        // 删除角色关联的资源
        roleResourceRepository.lambdaUpdate().eq(RoleResourceDO::getRoleId, roleId).remove();
        return true;
    }
}
