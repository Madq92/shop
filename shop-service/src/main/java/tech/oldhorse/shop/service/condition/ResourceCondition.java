package tech.oldhorse.shop.service.condition;

import lombok.Data;
import tech.oldhorse.shop.common.object.PageParam;

@Data
public class ResourceCondition extends PageParam {
    private String tenantId;
    private String nameLike;

    public ResourceCondition() {

    }

    public ResourceCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
