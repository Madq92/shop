package tech.oldhorse.shop.service;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.object.model.UserModel;

import java.util.List;

public interface UserService {
    String create(UserModel userModel);

    Boolean edit(UserModel userModel);

    Boolean delete(String userId);

    UserModel getByUserId(String userId);

    PageData<UserModel> pageByCondition(UserCondition condition);

    List<UserModel> listByCondition(UserCondition condition);
}
