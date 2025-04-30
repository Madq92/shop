package tech.oldhorse.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.dao.entity.OperLogDO;
import tech.oldhorse.shop.dao.repository.OperLogRepository;
import tech.oldhorse.shop.service.OperLogService;
import tech.oldhorse.shop.service.condition.OperLogCondition;
import tech.oldhorse.shop.service.convert.OperLogCoreConvert;
import tech.oldhorse.shop.service.object.model.OperLogModel;

@Slf4j
@Service
public class OperLogServiceImpl implements OperLogService {
    @Autowired
    OperLogRepository operLogRepository;
    @Autowired
    OperLogCoreConvert operLogCoreConvert;

    @Override
    public PageData<OperLogModel> pageByCondition(OperLogCondition condition) {
        Page<OperLogDO> page = operLogRepository.lambdaQuery().page(condition.getPage());
        return PageData.convertFrom(page, operLogCoreConvert::do2Model);
    }

    @Override
    public OperLogModel getByLogId(Long logId) {
        OperLogDO operLogDO = operLogRepository.getById(logId);
        return operLogCoreConvert.do2Model(operLogDO);
    }

    @Override
    public Boolean create(OperLogModel operLogModel) {
        OperLogDO operLogDO = operLogCoreConvert.model2Do(operLogModel);
        return operLogRepository.save(operLogDO);
    }
}
