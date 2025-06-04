package tech.oldhorse.shop.web.controller.prod;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.service.prod.CategoryService;
import tech.oldhorse.shop.service.prod.condition.CategoryCondition;
import tech.oldhorse.shop.service.prod.convert.CategoryCoreConvert;
import tech.oldhorse.shop.service.prod.object.dto.CategoryDTO;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;

import java.util.List;

@Tag(name = "分类管理", description = "分类的增删改查操作")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryCoreConvert categoryCoreConvert;

    @SaCheckPermission("category.view")
    @Operation(summary = "分类列表")
    @GetMapping
    public Result<List<CategoryDTO>> page(@RequestParam("parentId") String parentId,
                                          @RequestParam(required = false, value = "name") String name) {
        CategoryCondition condition = new CategoryCondition();
        condition.setNameLike(name);
        condition.setParentId(parentId);

        List<CategoryModel> categoryModels = categoryService.listByCondition(condition);
        return Result.success(categoryCoreConvert.modelList2DtoList(categoryModels));
    }

    @SaCheckPermission("category.view")
    @Operation(summary = "分类详情")
    @GetMapping("/{categoryId}")
    public Result<CategoryDTO> detail(@PathVariable("categoryId") String categoryId) {
        CategoryModel byCategoryId = categoryService.getByCategoryId(categoryId);
        return Result.success(byCategoryId, categoryCoreConvert::model2Dto);
    }

    @SaCheckPermission("category.create")
    @Operation(summary = "分类创建")
    @PostMapping
    public Result<String> create(@RequestBody CategoryDTO categoryDTO) {
        return Result.success(categoryService.create(categoryCoreConvert.dto2Model(categoryDTO)));
    }

    @SaCheckPermission("category.update")
    @Operation(summary = "分类编辑")
    @PutMapping("/{categoryId}")
    public Result<Boolean> edit(@PathVariable("categoryId") String categoryId, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(categoryId);
        return Result.success(categoryService.edit(categoryCoreConvert.dto2Model(categoryDTO)));
    }

    @SaCheckPermission("category.delete")
    @Operation(summary = "分类删除")
    @DeleteMapping("/{categoryId}")
    public Result<Boolean> delete(@PathVariable("categoryId") String categoryId) {
        return Result.success(categoryService.delete(categoryId));
    }
}
