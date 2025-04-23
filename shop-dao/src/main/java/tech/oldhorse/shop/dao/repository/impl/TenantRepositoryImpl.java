package tech.oldhorse.shop.service.impl;

import tech.oldhorse.shop.dao.entity.TenantDO;
import tech.oldhorse.shop.dao.mapper.TenantMapper;
import tech.oldhorse.shop.service.TenantRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class TenantRepositoryImpl extends ServiceImpl<TenantMapper, TenantDO> implements TenantRepository {

}
