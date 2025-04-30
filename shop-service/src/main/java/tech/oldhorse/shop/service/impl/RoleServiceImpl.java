package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.entity.RoleDO;
import tech.oldhorse.shop.dao.repository.RoleRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
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

    @Override
    public List<RoleModel> listByCondition(RoleCondition condition) {
        List<RoleDO> list = roleRepository.lambdaQuery().eq(RoleDO::getDeletedFlag, false).list();
        return roleCoreConvert.doList2ModelList(list);
    }

    @Override
    public RoleModel getByRoleId(String roleId) {
        RoleDO one = roleRepository.lambdaQuery().eq(RoleDO::getDeletedFlag, false).eq(RoleDO::getRoleId, roleId).one();
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

        RoleDO roleDO = roleCoreConvert.model2Do(roleModel);
        roleDO.setId(roleInDb.getId());
        return roleRepository.updateById(roleDO);
    }

    @Override
    public Boolean delete(String roleId) {
        RoleModel roleInDb = getByRoleId(roleId);

        RoleDO update = new RoleDO();
        update.setId(roleInDb.getId());
        update.setDeletedFlag(true);
        return roleRepository.updateById(update);
    }

    @Override
    public Boolean addResource(String roleId, RoleAddResourceReq req) {

        return null;
    }

    @Override
    public Boolean delResource(String roleId, RoleDelResourceReq req) {
        return null;
    }
}
