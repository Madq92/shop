package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.dao.base.BaseEntity;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author mika
 * @since 2025-04-21
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
     * 状态
     */
    private String status;
}
