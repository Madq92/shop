package tech.oldhorse.shop.service.object.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.object.dto.UserDTO;

@Data
public class UserLoginInfoResp {
    @Schema(description = "token")
    private String token;
    @Schema(description = "用户信息")
    private UserDTO user;
}
