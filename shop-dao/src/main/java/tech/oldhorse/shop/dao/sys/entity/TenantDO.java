package tech.oldhorse.shop.dao.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Getter
@Setter
@ToString
@TableName("tenant")
public class TenantDO extends BaseEntity {

    /**
     * root用户ID
     */
    private String rootUserId;

    /**
     * 状态
     */
    private String status;
}
