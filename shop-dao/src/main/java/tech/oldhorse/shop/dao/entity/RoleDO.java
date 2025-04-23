package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Getter
@Setter
@ToString
@TableName("role")
public class RoleDO extends BaseEntity {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;
}
