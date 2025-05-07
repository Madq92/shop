package tech.oldhorse.shop.service.object.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserUpdatePasswordReq {
    @Schema(description = "新密码")
    private String newPassword;
    @Schema(description = "旧密码")
    private String oldPassword;
}
