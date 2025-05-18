package tech.oldhorse.shop.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.service.RoleService;
import tech.oldhorse.shop.service.condition.RoleCondition;
import tech.oldhorse.shop.service.convert.RoleCoreConvert;
import tech.oldhorse.shop.service.object.dto.RoleDTO;
import tech.oldhorse.shop.service.object.model.RoleModel;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Tag(name = "角色管理", description = "角色的增删改查操作")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleCoreConvert roleCoreConvert;

    @SaCheckPermission("role.view")
    @Operation(summary = "角色列表")
    @GetMapping
    public Result<List<RoleDTO>> list() {
        RoleCondition condition = new RoleCondition();
        List<RoleModel> roleModels = roleService.listByCondition(condition);
        return Result.success(roleCoreConvert.modelList2DtoList(roleModels));
    }

    @SaCheckPermission("role.view")
    @Operation(summary = "角色详情")
    @GetMapping("/{roleId}")
    public Result<RoleDTO> detail(@PathVariable("roleId") String roleId) {
        RoleModel roleModel = roleService.getDetail(roleId);
        return Result.success(roleModel, roleCoreConvert::model2Dto);
    }

    @SaCheckPermission("role.create")
    @Operation(summary = "角色创建")
    @PostMapping
    public Result<String> create(@RequestBody RoleDTO roleDTO) {
        String roleId = roleService.create(roleCoreConvert.dto2Model(roleDTO));
        return Result.success(roleId);
    }

    @SaCheckPermission("role.update")
    @Operation(summary = "角色编辑")
    @PutMapping("/{roleId}")
    public Result<Boolean> edit(@PathVariable("roleId") String roleId, @RequestBody RoleDTO roleDTO) {
        roleDTO.setRoleId(roleId);
        return Result.success(roleService.edit(roleCoreConvert.dto2Model(roleDTO)));
    }

    @SaCheckPermission("role.delete")
    @Operation(summary = "角色删除")
    @DeleteMapping("/{roleId}")
    public Result<Boolean> delete(@PathVariable("roleId") String roleId) {
        return Result.success(roleService.delete(roleId));
    }
}
