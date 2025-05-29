package tech.oldhorse.shop.service.sys.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    @Schema(description = "角色ID")
    private String roleId;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色权限字符串")
    private String roleKey;
    @Schema(description = "显示顺序")
    private Integer sort;
    @Schema(description = "资源列表")
    private List<ResourceDTO> resources;
}
