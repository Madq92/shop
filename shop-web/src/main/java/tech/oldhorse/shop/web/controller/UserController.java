package tech.oldhorse.shop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.object.dto.UserDTO;
import tech.oldhorse.shop.service.object.model.UserModel;
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;
import tech.oldhorse.shop.service.object.request.UserLoginReq;
import tech.oldhorse.shop.service.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.object.response.UserLoginInfoResp;
import tech.oldhorse.shop.web.convert.UserConvert;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
@Tag(name = "用户管理", description = "用户的增删改查操作，登录登出")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserConvert userConvert;

    @Operation(summary = "用户分页")
    @GetMapping
    public Result<PageData<UserDTO>> page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        UserCondition condition = new UserCondition(pageNum, pageSize);
        PageData<UserModel> page = userService.pageByCondition(condition);
        return Result.success(PageUtil.makeResponse(page, userConvert::model2Dto));
    }

    @Operation(summary = "用户详情")
    @GetMapping("/{userId}")
    public Result<UserDTO> detail(@PathVariable("userId") String userId) {
        UserModel userModel = userService.getByUserId(userId);
        return Result.success(userModel, userConvert::model2Dto);
    }

    @Operation(summary = "用户创建")
    @PostMapping
    public Result<String> create(@RequestBody UserDTO userDTO) {
        String userId = userService.create(userConvert.dto2Model(userDTO));
        return Result.success(userId);
    }

    @Operation(summary = "用户编辑")
    @PutMapping("/{userId}")
    public Result<Boolean> edit(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        return Result.success(userService.edit(userConvert.dto2Model(userDTO)));
    }

    @Operation(summary = "用户删除")
    @DeleteMapping("/{userId}")
    public Result<Boolean> delete(@PathVariable("userId") String userId) {
        return Result.success(userService.delete(userId));
    }

    @Operation(summary = "用户添加角色")
    @PostMapping("/{userId}/role")
    public Result<Boolean> addRole(@PathVariable("userId") String userId, @RequestBody RoleAddResourceReq req) {
        return Result.success(userService.addRole(userId, req));
    }

    @Operation(summary = "角色删除资源")
    @DeleteMapping("/{userId}/role")
    public Result<Boolean> delRole(@PathVariable("userId") String userId, @RequestBody RoleDelResourceReq req) {
        return Result.success(userService.delRole(userId, req));
    }

    @Operation(summary = "用户更新密码")
    @PostMapping("/{userId}/update-password")
    public Result<Boolean> updatePassword(@PathVariable("userId") String userId, @RequestBody UserUpdatePasswordReq req) {
        return Result.success(userService.updatePassword(userId, req));
    }

    @Operation(summary = "用户登录信息")
    @GetMapping("/{userId}/current-login-info")
    public Result<UserLoginInfoResp> loginInfo(@PathVariable("userId") String userId) {
        return Result.success(userService.loginInfo(userId));
    }

    @Operation(summary = "用户资源")
    @GetMapping("/{userId}/resource")
    public Result<Boolean> resource(@PathVariable("userId") String userId) {
        return Result.success(userService.delete(userId));
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<UserLoginInfoResp> login(@RequestBody UserLoginReq req) {
        return Result.success(userService.login(req));
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result<Boolean> logout() {
        String userId = "123";
        return Result.success(userService.logout(userId));
    }
}

