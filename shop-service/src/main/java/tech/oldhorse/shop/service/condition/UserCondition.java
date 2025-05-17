package tech.oldhorse.shop.service.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;
import tech.oldhorse.shop.service.enums.UserGenderEnum;
import tech.oldhorse.shop.service.enums.UserStatusEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCondition extends PageParam {
    private String nameLike;
    private String email;
    private String phonenumber;
    private UserStatusEnum status;
    private UserGenderEnum gender;

    public UserCondition() {

    }

    public UserCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
