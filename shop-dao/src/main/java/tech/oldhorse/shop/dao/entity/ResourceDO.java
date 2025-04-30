package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Getter
@Setter
@ToString
@TableName("resource")
public class ResourceDO extends BaseEntity {

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 父资源ID
     */
    private String parentResourceId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 菜单类型（MENU菜单 BUTTON按钮）
     */
    private String resourceType;

    /**
     * 菜单状态
     */
    private Boolean visible;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;
}
