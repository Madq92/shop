package tech.oldhorse.shop.service.condition;

import lombok.Data;
import tech.oldhorse.shop.common.object.PageParam;

@Data
public class UserCondition extends PageParam {
    private String tenantId;
    private String nameLike;
}
