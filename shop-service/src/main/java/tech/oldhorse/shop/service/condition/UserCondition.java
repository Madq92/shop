package tech.oldhorse.shop.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCondition extends PageParam {
    private String nameLike;
    private String email;
    private String phonenumber;

    public UserCondition() {

    }

    public UserCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
