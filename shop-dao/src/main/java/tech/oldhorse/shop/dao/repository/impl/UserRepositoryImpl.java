package tech.oldhorse.shop.service.impl;

import tech.oldhorse.shop.dao.entity.UserDO;
import tech.oldhorse.shop.dao.mapper.UserMapper;
import tech.oldhorse.shop.service.UserRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserDO> implements UserRepository {

}
