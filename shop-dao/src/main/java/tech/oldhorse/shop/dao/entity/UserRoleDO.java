package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author mika
 * @since 2025-04-30
 */
@Getter
@Setter
@ToString
@TableName("user_role")
public class UserRoleDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    public UserRoleDO(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
