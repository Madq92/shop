package tech.oldhorse.shop.service.object.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.object.dto.RoleDTO;
import tech.oldhorse.shop.service.object.dto.UserDTO;

import java.util.List;

@Data
public class UserLoginInfoResp {
    @Schema(description = "token")
    private String token;
    @Schema(description = "用户信息")
    private UserDTO user;
    @Schema(description = "用户角色")
    private List<RoleDTO> roleList;
    @Schema(description = "用户资源")
    private List<ResourceDTO> resourceList;
}
