package tech.oldhorse.shop.web.controller.sys;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Tag(name = "租户管理", description = "租户的增删改查操作")
@RestController
@RequestMapping("/tenant")
public class TenantController {

}

