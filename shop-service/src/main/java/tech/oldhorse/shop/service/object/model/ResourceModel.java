package tech.oldhorse.shop.service.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.enums.ResourceTypeEnum;

@Data
public class ResourceModel extends BaseModel {
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
    private Integer sort;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 菜单类型（MENU菜单 BUTTON按钮）
     */
    private ResourceTypeEnum resourceType;

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
