package tech.oldhorse.shop.service.object.dto;

import lombok.Data;

@Data
public class UserDTO {
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
