package tech.oldhorse.shop.service.object.model;

import lombok.Data;

@Data
public class UserModel {
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
    private String status;
}
