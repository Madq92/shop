package tech.oldhorse.shop.service.sys.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.sys.enums.ResourceTypeEnum;

@Data
public class ResourceDTO {
    @Schema(description = "资源ID")
    private String resourceId;
    @Schema(description = "资源名称")
    private String resourceName;
    @Schema(description = "父资源ID")
    private String parentResourceId;
    @Schema(description = "显示顺序")
    private Integer sort;
    @Schema(description = "请求地址")
    private String url;
    @Schema(description = "菜单类型")
    private ResourceTypeEnum resourceType;
    @Schema(description = "菜单状态")
    private Boolean visible;
    @Schema(description = "权限标识")
    private String perms;
    @Schema(description = "菜单图标")
    private String icon;
}
