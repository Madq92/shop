package tech.oldhorse.shop.web.controller.sys;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.oldhorse.shop.common.context.WebContextHolder;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.service.sys.UserService;
import tech.oldhorse.shop.service.sys.object.request.UserLoginReq;
import tech.oldhorse.shop.service.sys.object.response.UserLoginInfoResp;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
@Tag(name = "登录管理", description = "登录登出操作")
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    @SaIgnore
    public Result<UserLoginInfoResp> login(@RequestBody UserLoginReq req) {
        return Result.success(userService.login(req));
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result<Boolean> logout() {
        String userId = WebContextHolder.getUserId();
        return Result.success(userService.logout(userId));
    }
}

