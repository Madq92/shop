package tech.oldhorse.shop.service.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.enums.UserStatusEnum;

@Data
public class UserModel extends BaseModel {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 状态
     */
    private UserStatusEnum status;
}
