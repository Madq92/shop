package tech.oldhorse.shop.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.context.WebContextHolder;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.EnumUtils;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.convert.UserCoreConvert;
import tech.oldhorse.shop.service.enums.UserGenderEnum;
import tech.oldhorse.shop.service.enums.UserStatusEnum;
import tech.oldhorse.shop.service.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.object.dto.RoleDTO;
import tech.oldhorse.shop.service.object.dto.UserDTO;
import tech.oldhorse.shop.service.object.model.ResourceModel;
import tech.oldhorse.shop.service.object.model.RoleModel;
import tech.oldhorse.shop.service.object.model.UserModel;
import tech.oldhorse.shop.service.object.request.UserLoginReq;
import tech.oldhorse.shop.service.object.request.UserUpdatePasswordReq;
import tech.oldhorse.shop.service.object.response.UserLoginInfoResp;

import java.util.List;

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
    UserCoreConvert userCoreConvert;
    @Autowired
    ResourceCoreConvert resourceCoreConvert;
    @Autowired
    RoleCoreConvert roleCoreConvert;

    @SaCheckPermission("user.view")
    @Operation(summary = "用户分页")
    @GetMapping
    public Result<PageData<UserDTO>> page(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam(required = false, value = "name") String name,
                                          @RequestParam(required = false, value = "email") String email,
                                          @RequestParam(required = false, value = "phonenumber") String phonenumber,
                                          @RequestParam(required = false, value = "status") String status,
                                          @RequestParam(required = false, value = "gender") String gender
    ) {
        UserCondition condition = new UserCondition(pageNum, pageSize);
        condition.setNameLike(name);
        condition.setEmail(email);
        condition.setPhonenumber(phonenumber);
        condition.setStatus(EnumUtils.getByName(UserStatusEnum.class, status));
        condition.setGender(EnumUtils.getByName(UserGenderEnum.class, gender));

        PageData<UserModel> page = userService.pageByCondition(condition);
        return Result.success(PageUtil.makeResponse(page, userCoreConvert::model2Dto));
    }

    @SaCheckPermission("user.view")
    @Operation(summary = "用户详情")
    @GetMapping("/{userId}")
    public Result<UserDTO> detail(@PathVariable("userId") String userId) {
        UserModel userModel = userService.detaile(userId);
        return Result.success(userModel, userCoreConvert::model2Dto);
    }

    @SaCheckPermission("user.create")
    @Operation(summary = "用户创建")
    @PostMapping
    public Result<String> create(@RequestBody UserDTO userDTO) {
        String userId = userService.create(userCoreConvert.dto2Model(userDTO));
        return Result.success(userId);
    }

    @SaCheckPermission("user.update")
    @Operation(summary = "用户编辑")
    @PutMapping("/{userId}")
    public Result<Boolean> edit(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        return Result.success(userService.edit(userCoreConvert.dto2Model(userDTO)));
    }

    @SaCheckPermission("user.delete")
    @Operation(summary = "用户删除")
    @DeleteMapping("/{userId}")
    public Result<Boolean> delete(@PathVariable("userId") String userId) {
        return Result.success(userService.delete(userId));
    }

    @SaCheckPermission("user.role.view")
    @Operation(summary = "用户角色")
    @GetMapping("/{userId}/role")
    public Result<List<RoleDTO>> userRole(@PathVariable("userId") String userId) {
        List<RoleModel> roleModels = userService.getUserRole(userId);
        return Result.success(roleCoreConvert.modelList2DtoList(roleModels));
    }


    @SaCheckPermission("user.resource.view")
    @Operation(summary = "用户资源")
    @GetMapping("/{userId}/resource")
    public Result<List<ResourceDTO>> userResource(@PathVariable("userId") String userId) {
        List<ResourceModel> resourceModels = userService.getUserResource(userId);
        return Result.success(resourceCoreConvert.modelList2DtoList(resourceModels));
    }

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

    @SaCheckPermission("user.password.update")
    @Operation(summary = "用户更新密码")
    @PostMapping("/update-password")
    public Result<Boolean> updatePassword(@RequestBody UserUpdatePasswordReq req) {
        String userId = WebContextHolder.getUserId();
        return Result.success(userService.updatePassword(userId, req));
    }

}

