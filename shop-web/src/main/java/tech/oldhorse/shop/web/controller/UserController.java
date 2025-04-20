package tech.oldhorse.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.oldhorse.shop.service.IUserService;
import tech.oldhorse.shop.service.condition.UserCondition;
import tech.oldhorse.shop.web.User;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-21
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping
    @ResponseBody
    public List<UserDTO> user() {
        UserCondition condition = new UserCondition();
        userService.listByCondition(condition);
        return null;
    }
}

