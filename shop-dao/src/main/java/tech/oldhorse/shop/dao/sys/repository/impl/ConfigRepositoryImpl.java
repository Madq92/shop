package tech.oldhorse.shop.dao.sys.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.entity.ConfigDO;
import tech.oldhorse.shop.dao.sys.mapper.ConfigMapper;
import tech.oldhorse.shop.dao.sys.repository.ConfigRepository;

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
