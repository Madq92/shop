package tech.oldhorse.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.UserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.service.object.dto.UserDTO;
import tech.oldhorse.shop.service.object.model.UserModel;
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

    @DeleteMapping("/{userId}")
    public Result<Boolean> delete(@PathVariable("userId") String userId) {
        return Result.success(userService.delete(userId));
    }
}

