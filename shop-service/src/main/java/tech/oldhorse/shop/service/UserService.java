package tech.oldhorse.shop.service;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.object.model.UserModel;
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;
import tech.oldhorse.shop.service.object.request.UserLoginReq;
import tech.oldhorse.shop.service.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.object.response.UserLoginInfoResp;

import java.util.List;

public interface UserService {
    String create(UserModel userModel);

    Boolean edit(UserModel userModel);

    Boolean delete(String userId);

    UserModel getByUserId(String userId);

    PageData<UserModel> pageByCondition(UserCondition condition);

    List<UserModel> listByCondition(UserCondition condition);

    Boolean updatePassword(String userId, UserUpdatePasswordReq req);

    UserLoginInfoResp loginInfo(String userId);

    UserLoginInfoResp login(UserLoginReq req);

    Boolean logout(String userId);

    Boolean addRole(String userId, RoleAddResourceReq req);

    Boolean delRole(String userId, RoleDelResourceReq req);
}
