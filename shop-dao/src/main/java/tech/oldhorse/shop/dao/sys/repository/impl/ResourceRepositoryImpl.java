package tech.oldhorse.shop.dao.sys.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.entity.ResourceDO;
import tech.oldhorse.shop.dao.sys.mapper.ResourceMapper;
import tech.oldhorse.shop.dao.sys.repository.ResourceRepository;

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
