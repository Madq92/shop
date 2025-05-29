package tech.oldhorse.shop.web.controller.sys;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.EnumUtils;
import tech.oldhorse.shop.service.sys.ResourceService;
import tech.oldhorse.shop.service.sys.condition.ResourceCondition;
import tech.oldhorse.shop.service.sys.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.sys.enums.ResourceTypeEnum;
import tech.oldhorse.shop.service.sys.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.sys.object.model.ResourceModel;

import java.util.List;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Tag(name = "资源管理", description = "资源的增删改查操作")
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceCoreConvert resourceCoreConvert;

    @SaCheckPermission("resource.view")
    @Operation(summary = "资源列表")
    @GetMapping
    public Result<List<ResourceDTO>> list( @RequestParam(required = false, value = "type") String type) {
        ResourceCondition condition = new ResourceCondition();
        condition.setType(EnumUtils.getByName(ResourceTypeEnum.class, type));
        List<ResourceModel> resourceModels = resourceService.listByCondition(condition);
        return Result.success(resourceCoreConvert.modelList2DtoList(resourceModels));
    }

    @SaCheckPermission("resource.view")
    @Operation(summary = "资源详情")
    @GetMapping("/{resourceId}")
    public Result<ResourceDTO> detail(@PathVariable("resourceId") String resourceId) {
        ResourceModel userModel = resourceService.getByResourceId(resourceId);
        return Result.success(userModel, resourceCoreConvert::model2Dto);
    }

    @SaCheckPermission("resource.create")
    @Operation(summary = "资源创建")
    @PostMapping
    public Result<String> create(@RequestBody ResourceDTO resourceDTO) {
        String resourceId = resourceService.create(resourceCoreConvert.dto2Model(resourceDTO));
        return Result.success(resourceId);
    }

    @SaCheckPermission("resource.update")
    @Operation(summary = "资源编辑")
    @PutMapping("/{resourceId}")
    public Result<Boolean> edit(@PathVariable("resourceId") String resourceId, @RequestBody ResourceDTO resourceDTO) {
        resourceDTO.setResourceId(resourceId);
        return Result.success(resourceService.edit(resourceCoreConvert.dto2Model(resourceDTO)));
    }

    @SaCheckPermission("resource.delete")
    @Operation(summary = "资源删除")
    @DeleteMapping("/{resourceId}")
    public Result<Boolean> delete(@PathVariable("resourceId") String resourceId) {
        return Result.success(resourceService.delete(resourceId));
    }
}

