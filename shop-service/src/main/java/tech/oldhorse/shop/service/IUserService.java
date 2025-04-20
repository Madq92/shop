package tech.oldhorse.shop.service;

import tech.oldhorse.shop.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.model.UserModel;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
public interface IUserService extends IService<User> {

    List<UserModel> listByCondition(UserCondition condition);

}
