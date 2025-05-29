package tech.oldhorse.shop.dao.sys.repository.impl;

import tech.oldhorse.shop.dao.sys.entity.UserRoleDO;
import tech.oldhorse.shop.dao.sys.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.repository.UserRoleRepository;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-30
 */
@Service
public class UserRoleRepositoryImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleRepository {

}
