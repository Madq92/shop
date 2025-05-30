package tech.oldhorse.shop.dao.prod.repository.impl;

import tech.oldhorse.shop.dao.prod.entity.DictDO;
import tech.oldhorse.shop.dao.prod.mapper.DictMapper;
import tech.oldhorse.shop.dao.prod.repository.DictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Service
public class DictRepositoryImpl extends ServiceImpl<DictMapper, DictDO> implements DictRepository {

}
