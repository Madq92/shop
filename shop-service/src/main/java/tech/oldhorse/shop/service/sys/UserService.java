package tech.oldhorse.shop.service.sys;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.sys.condition.UserCondition;
import tech.oldhorse.shop.service.sys.object.model.ResourceModel;
import tech.oldhorse.shop.service.sys.object.model.RoleModel;
import tech.oldhorse.shop.service.sys.object.model.UserModel;
import tech.oldhorse.shop.service.sys.object.request.UserLoginReq;
import tech.oldhorse.shop.service.sys.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.sys.object.response.UserLoginInfoResp;

import java.util.List;

public interface UserService {
    String create(UserModel userModel);

    Boolean edit(UserModel userModel);

    Boolean delete(String userId);

    UserModel getByUserId(String userId);

    UserModel detaile(String userId);

    PageData<UserModel> pageByCondition(UserCondition condition);

    List<UserModel> listByCondition(UserCondition condition);

    List<ResourceModel> getUserResource(String userId);

    List<RoleModel> getUserRole(String userId);

    UserLoginInfoResp login(UserLoginReq req);

    Boolean logout(String userId);

    Boolean updatePassword(String userId, UserUpdatePasswordReq req);

}
