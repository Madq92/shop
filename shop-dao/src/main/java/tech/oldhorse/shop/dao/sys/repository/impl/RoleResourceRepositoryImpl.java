package tech.oldhorse.shop.dao.sys.repository.impl;

import tech.oldhorse.shop.dao.sys.entity.RoleResourceDO;
import tech.oldhorse.shop.dao.sys.mapper.RoleResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.repository.RoleResourceRepository;

/**
 * <p>
 * 角色和资源关联表 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-30
 */
@Service
public class RoleResourceRepositoryImpl extends ServiceImpl<RoleResourceMapper, RoleResourceDO> implements RoleResourceRepository {

}
