package tech.oldhorse.shop.dao.sys.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.entity.UserDO;
import tech.oldhorse.shop.dao.sys.mapper.UserMapper;
import tech.oldhorse.shop.dao.sys.repository.UserRepository;

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
