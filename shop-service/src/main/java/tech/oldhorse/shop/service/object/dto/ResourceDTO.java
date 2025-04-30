package tech.oldhorse.shop.service.object.dto;

import lombok.Data;

@Data
public class ResourceDTO {
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
     * 菜单状态（0显示 1隐藏）
     */
    private Byte visible;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;
}
