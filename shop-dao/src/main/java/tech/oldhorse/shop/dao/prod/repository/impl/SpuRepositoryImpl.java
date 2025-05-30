package tech.oldhorse.shop.dao.prod.repository.impl;

import tech.oldhorse.shop.dao.prod.entity.SpuDO;
import tech.oldhorse.shop.dao.prod.mapper.SpuMapper;
import tech.oldhorse.shop.dao.prod.repository.SpuRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * SPU 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Service
public class SpuRepositoryImpl extends ServiceImpl<SpuMapper, SpuDO> implements SpuRepository {

}
