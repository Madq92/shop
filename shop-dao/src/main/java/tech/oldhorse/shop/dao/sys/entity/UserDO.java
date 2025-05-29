package tech.oldhorse.shop.dao.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

import java.time.LocalDateTime;
/**
 * <p>
 * 用户
 * </p>
 *
 * @author mika
 * @since 2025-05-17
 */
@Getter
@Setter
@ToString
@TableName("user")
public class UserDO extends BaseEntity {

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
    private String gender;

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
    private String status;
}
