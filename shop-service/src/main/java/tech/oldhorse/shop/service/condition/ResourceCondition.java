package tech.oldhorse.shop.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceCondition extends PageParam {
    public ResourceCondition() {

    }

    public ResourceCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
