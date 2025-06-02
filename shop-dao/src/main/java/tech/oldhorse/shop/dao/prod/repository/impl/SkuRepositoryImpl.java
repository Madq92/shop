package tech.oldhorse.shop.dao.prod.repository.impl;

import tech.oldhorse.shop.dao.prod.entity.SkuDO;
import tech.oldhorse.shop.dao.prod.mapper.SkuMapper;
import tech.oldhorse.shop.dao.prod.repository.SkuRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-06-02
 */
@Service
public class SkuRepositoryImpl extends ServiceImpl<SkuMapper, SkuDO> implements SkuRepository {

}
