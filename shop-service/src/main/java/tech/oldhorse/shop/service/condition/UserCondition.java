package tech.oldhorse.shop.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCondition extends PageParam {
    private String tenantId;
    private String nameLike;

    public UserCondition() {

    }

    public UserCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
