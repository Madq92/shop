package tech.oldhorse.shop.web.controller;

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
import tech.oldhorse.shop.service.object.request.RoleAddResourceReq;
import tech.oldhorse.shop.service.object.request.RoleDelResourceReq;

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

    @Operation(summary = "角色列表")
    @GetMapping
    public Result<List<RoleDTO>> list() {
        RoleCondition condition = new RoleCondition();
        List<RoleModel> roleModels = roleService.listByCondition(condition);
        return Result.success(roleCoreConvert.modelList2DtoList(roleModels));
    }

    @Operation(summary = "角色详情")
    @GetMapping("/{roleId}")
    public Result<RoleDTO> detail(@PathVariable("roleId") String roleId) {
        RoleModel roleModel = roleService.getByRoleId(roleId);
        return Result.success(roleModel, roleCoreConvert::model2Dto);
    }

    @Operation(summary = "角色创建")
    @PostMapping
    public Result<String> create(@RequestBody RoleDTO roleDTO) {
        String roleId = roleService.create(roleCoreConvert.dto2Model(roleDTO));
        return Result.success(roleId);
    }

    @Operation(summary = "角色编辑")
    @PutMapping("/{roleId}")
    public Result<Boolean> edit(@PathVariable("roleId") String roleId, @RequestBody RoleDTO roleDTO) {
        roleDTO.setRoleId(roleId);
        return Result.success(roleService.edit(roleCoreConvert.dto2Model(roleDTO)));
    }

    @Operation(summary = "角色删除")
    @DeleteMapping("/{roleId}")
    public Result<Boolean> delete(@PathVariable("roleId") String roleId) {
        return Result.success(roleService.delete(roleId));
    }

    @Operation(summary = "角色添加资源")
    @PostMapping("/{roleId}/resource")
    public Result<Boolean> addResource(@PathVariable("roleId") String roleId, @RequestBody RoleAddResourceReq req) {
        return Result.success(roleService.addResource(roleId, req));
    }

    @Operation(summary = "角色删除资源")
    @DeleteMapping("/{roleId}/resource")
    public Result<Boolean> delResource(@PathVariable("roleId") String roleId, @RequestBody RoleDelResourceReq req) {
        return Result.success(roleService.delResource(roleId, req));
    }
}
