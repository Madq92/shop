package tech.oldhorse.shop.service.sys;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.sys.condition.OperLogCondition;
import tech.oldhorse.shop.service.sys.object.model.OperLogModel;

public interface OperLogService {
    PageData<OperLogModel> pageByCondition(OperLogCondition condition);

    OperLogModel getByLogId(Long logId);

    Boolean create(OperLogModel operLogModel);
}
