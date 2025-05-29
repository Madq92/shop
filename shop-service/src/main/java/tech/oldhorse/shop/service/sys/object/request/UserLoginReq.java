package tech.oldhorse.shop.service.sys.object.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginReq {
    @Schema(description = "用户邮箱")
    private String email;
    @Schema(description = "手机号码")
    private String phonenumber;
    @Schema(description = "用户密码")
    private String password;
}
