package tech.oldhorse.shop.service;

import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.object.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> listByCondition(RoleCondition condition);

    RoleModel getByRoleId(String roleId);

    RoleModel getDetail(String roleId);

    String create(RoleModel roleModel);

    Boolean edit(RoleModel roleModel);

    Boolean delete(String roleId);
}
