package tech.oldhorse.shop.service.impl;

import tech.oldhorse.shop.dao.entity.ResourceDO;
import tech.oldhorse.shop.dao.mapper.ResourceMapper;
import tech.oldhorse.shop.service.ResourceRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class ResourceRepositoryImpl extends ServiceImpl<ResourceMapper, ResourceDO> implements ResourceRepository {

}
