package tech.oldhorse.shop.dao.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色和资源关联表
 * </p>
 *
 * @author mika
 * @since 2025-04-30
 */
@Getter
@Setter
@ToString
@TableName("sys_role_resource")
public class RoleResourceDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 租户ID
     */
    private String tenantId;

    public RoleResourceDO(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
