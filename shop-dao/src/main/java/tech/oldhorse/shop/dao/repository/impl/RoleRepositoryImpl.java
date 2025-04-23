package tech.oldhorse.shop.service.impl;

import tech.oldhorse.shop.dao.entity.RoleDO;
import tech.oldhorse.shop.dao.mapper.RoleMapper;
import tech.oldhorse.shop.service.RoleRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
