package tech.oldhorse.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.entity.User;
import tech.oldhorse.shop.dao.mapper.UserMapper;
import tech.oldhorse.shop.service.IUserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.convert.UserConvert;
import tech.oldhorse.shop.service.model.UserModel;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserConvert userConvert;

    @Override
    public List<UserModel> listByCondition(UserCondition condition) {
        List<User> list = this.lambdaQuery().eq(User::getTenantId, condition.getTenantId()).list();
        return userConvert.doList2ModelList(list);
    }
}
