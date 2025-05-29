package tech.oldhorse.shop.service.sys.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.sys.enums.UserGenderEnum;
import tech.oldhorse.shop.service.sys.enums.UserStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

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
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private UserGenderEnum gender;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 密码最后更新时间
     */
    private LocalDateTime pwdUpdateDate;


    /**
     * 状态
     */
    private UserStatusEnum status;

    /**
     * 用户角色
     */
    private List<RoleModel> roles;

    /**
     * 用户所有资源
     */
    private List<ResourceModel> resources;
}
