package tech.oldhorse.shop.service;

import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.object.model.RoleModel;
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;

import java.util.List;

public interface RoleService {
    List<RoleModel> listByCondition(RoleCondition condition);

    RoleModel getByRoleId(String roleId);

    String create(RoleModel roleModel);

    Boolean edit(RoleModel roleModel);

    Boolean delete(String roleId);

    Boolean addResource(String roleId, RoleAddResourceReq req);

    Boolean delResource(String roleId, RoleDelResourceReq req);
}
