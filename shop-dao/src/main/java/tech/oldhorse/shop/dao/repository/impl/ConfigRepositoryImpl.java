package tech.oldhorse.shop.dao.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.entity.ConfigDO;
import tech.oldhorse.shop.dao.mapper.ConfigMapper;
import tech.oldhorse.shop.dao.repository.ConfigRepository;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class ConfigRepositoryImpl extends ServiceImpl<ConfigMapper, ConfigDO> implements ConfigRepository {

}
