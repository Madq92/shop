package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.repository.ResourceRepository;
import tech.oldhorse.shop.dao.repository.RoleRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.convert.ResourceCoreConvert;
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
    RoleRepository resourceRepository;
    @Autowired
    RoleCoreConvert roleCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public List<RoleModel> listByCondition(RoleCondition condition) {
        return List.of();
    }

    @Override
    public RoleModel getByRoleId(String roleId) {
        return null;
    }

    @Override
    public String create(RoleModel roleModel) {
        return "";
    }

    @Override
    public Boolean edit(RoleModel roleModel) {
        return null;
    }

    @Override
    public Boolean delete(String roleId) {
        return null;
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
