package tech.oldhorse.shop.service.sys;

import tech.oldhorse.shop.service.sys.condition.RoleCondition;
import tech.oldhorse.shop.service.sys.object.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> listByCondition(RoleCondition condition);

    RoleModel getByRoleId(String roleId);

    RoleModel getDetail(String roleId);

    String create(RoleModel roleModel);

    Boolean edit(RoleModel roleModel);

    Boolean delete(String roleId);
}
