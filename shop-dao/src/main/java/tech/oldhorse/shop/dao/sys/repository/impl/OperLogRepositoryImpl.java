package tech.oldhorse.shop.dao.sys.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.sys.entity.OperLogDO;
import tech.oldhorse.shop.dao.sys.mapper.OperLogMapper;
import tech.oldhorse.shop.dao.sys.repository.OperLogRepository;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Service
public class OperLogRepositoryImpl extends ServiceImpl<OperLogMapper, OperLogDO> implements OperLogRepository {

}
