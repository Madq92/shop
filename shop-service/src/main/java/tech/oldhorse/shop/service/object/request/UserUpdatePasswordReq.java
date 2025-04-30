package tech.oldhorse.shop.service.object.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserUpdatePasswordReq {
    @Schema(description = "用户密码")
    private String password;
}
