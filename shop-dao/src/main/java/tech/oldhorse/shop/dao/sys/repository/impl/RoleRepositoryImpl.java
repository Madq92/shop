package tech.oldhorse.shop.dao.sys.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.entity.RoleDO;
import tech.oldhorse.shop.dao.sys.mapper.RoleMapper;
import tech.oldhorse.shop.dao.sys.repository.RoleRepository;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class RoleRepositoryImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleRepository {

}
