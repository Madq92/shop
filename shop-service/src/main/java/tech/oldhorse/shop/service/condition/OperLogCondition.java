package tech.oldhorse.shop.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class OperLogCondition extends PageParam {
    public OperLogCondition() {

    }

    public OperLogCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
