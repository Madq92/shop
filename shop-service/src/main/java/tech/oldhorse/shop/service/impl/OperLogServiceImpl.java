package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.dao.repository.OperLogRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
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
    @Autowired
    IdGeneratorWrapper idGenerator;
    @Override
    public PageData<OperLogModel> pageByCondition(OperLogCondition condition) {
        return null;
    }

    @Override
    public OperLogModel getByLogId(String logId) {
        return null;
    }

    @Override
    public Boolean create(OperLogModel operLogModel) {
        return null;
    }
}
