package tech.oldhorse.shop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.object.dto.UserDTO;
import tech.oldhorse.shop.service.object.model.UserModel;
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
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserConvert userConvert;

    @GetMapping
    public Result<PageData<UserDTO>> page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        UserCondition condition = new UserCondition(pageNum, pageSize);
        PageData<UserModel> page = userService.pageByCondition(condition);
        return Result.success(PageUtil.makeResponse(page, userConvert::model2Dto));
    }

    @GetMapping("/{userId}")
    public Result<UserDTO> detail(@PathVariable("userId") String userId) {
        UserModel userModel = userService.getByUserId(userId);
        return Result.success(userModel, userConvert::model2Dto);
    }

    @PostMapping
    public Result<String> create(@RequestBody UserDTO userDTO) {
        String userId = userService.create(userConvert.dto2Model(userDTO));
        return Result.success(userId);
    }

    @PutMapping("/{userId}")
    public Result<Boolean> edit(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        return Result.success(userService.edit(userConvert.dto2Model(userDTO)));
    }

//    @PostMapping("/{userId}/update-password")
//    public Result<Boolean> updatePassword(@PathVariable("userId") String userId, @RequestBody UserUpdatePasswordReq req) {
//        return Result.success(userService.updatePassword(userId, req));
//    }
//
//
//    @DeleteMapping("/{userId}")
//    public Result<Boolean> delete(@PathVariable("userId") String userId) {
//        return Result.success(userService.delete(userId));
//    }
//
//    @GetMapping("/{userId}/current-login-info")
//    public Result<UserLoginInfoResp> loginInfo(@PathVariable("userId") String userId) {
//        return Result.success(userService.loginInfo(userId));
//    }
//
//    @GetMapping("/{userId}/resource")
//    public Result<Boolean> resource(@PathVariable("userId") String userId) {
//        return Result.success(userService.delete(userId));
//    }


//    @PostMapping("/login")
//    public Result<UserLoginInfoResp> login(@RequestBody UserLoginReq req) {
//        return Result.success(userService.login(req));
//    }
//
//    @PostMapping("/logout")
//    public Result<Boolean> logout() {
//        String userId = "123";
//        return Result.success(userService.logout(userId));
//    }
}

