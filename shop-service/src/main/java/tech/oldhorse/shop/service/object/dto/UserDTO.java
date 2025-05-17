package tech.oldhorse.shop.service.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.enums.UserGenderEnum;
import tech.oldhorse.shop.service.enums.UserStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    @Schema(description = "用户ID")
    private String userId;
    @Schema(description = "用户名称")
    private String name;
    @Schema(description = "用户邮箱")
    private String email;
    @Schema(description = "手机号码")
    private String phonenumber;
    @Schema(description = "用户性别")
    private UserGenderEnum gender;
    @Schema(description = "头像路径")
    private String avatar;
    @Schema(description = "最后登录IP")
    private String loginIp;
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;
    @Schema(description = "密码最后更新时间")
    private LocalDateTime pwdUpdateDate;
    @Schema(description = "用户状态")
    private UserStatusEnum status;

    @Schema(description = "用户角色")
    private List<RoleDTO> roles;
    @Schema(description = "用户资源")
    private List<ResourceDTO> resources;
}
