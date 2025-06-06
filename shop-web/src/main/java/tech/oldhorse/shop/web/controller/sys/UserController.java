package tech.oldhorse.shop.web.controller.sys;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.context.WebContextHolder;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.EnumUtils;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.sys.UserService;
import tech.oldhorse.shop.service.sys.condition.UserCondition;
import tech.oldhorse.shop.service.sys.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.sys.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.sys.convert.UserCoreConvert;
import tech.oldhorse.shop.service.sys.enums.UserGenderEnum;
import tech.oldhorse.shop.service.sys.enums.UserStatusEnum;
import tech.oldhorse.shop.service.sys.object.dto.UserDTO;
import tech.oldhorse.shop.service.sys.object.model.UserModel;
import tech.oldhorse.shop.service.sys.object.request.UserUpdatePasswordReq;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
@Tag(name = "用户管理", description = "用户的增删改查操作")
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

    @SaCheckPermission("user.password.update")
    @Operation(summary = "用户更新密码")
    @PostMapping("/update-password")
    public Result<Boolean> updatePassword(@RequestBody UserUpdatePasswordReq req) {
        String userId = WebContextHolder.getUserId();
        return Result.success(userService.updatePassword(userId, req));
    }

}

