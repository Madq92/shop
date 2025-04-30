package tech.oldhorse.shop.service;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.condition.OperLogCondition;
import tech.oldhorse.shop.service.object.model.OperLogModel;

public interface OperLogService {
    PageData<OperLogModel> pageByCondition(OperLogCondition condition);

    OperLogModel getByLogId(Long logId);

    Boolean create(OperLogModel operLogModel);
}
