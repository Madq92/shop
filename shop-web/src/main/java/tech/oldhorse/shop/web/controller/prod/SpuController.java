package tech.oldhorse.shop.web.controller.prod;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.EnumUtils;
import tech.oldhorse.shop.common.utils.PageUtil;
import tech.oldhorse.shop.service.prod.SpuService;
import tech.oldhorse.shop.service.prod.condition.SpuCondition;
import tech.oldhorse.shop.service.prod.convert.SpuCoreConvert;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;
import tech.oldhorse.shop.service.prod.object.dto.SpuDTO;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

@Tag(name = "商品管理", description = "商品的增删改查操作")
@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    SpuService spuService;
    @Autowired
    SpuCoreConvert spuCoreConvert;

    @SaCheckPermission("spu.view")
    @Operation(summary = "商品分页")
    @GetMapping
    public Result<PageData<SpuDTO>> page(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(required = false, value = "name") String name,
                                         @RequestParam(required = false, value = "status") String status

    ) {
        SpuCondition condition = new SpuCondition(pageNum, pageSize);
        condition.setNameLike(name);
        condition.setStatus(EnumUtils.getByName(ProdStatusEnum.class, status));

        PageData<SpuModel> page = spuService.pageByCondition(condition);
        return Result.success(PageUtil.makeResponse(page, spuCoreConvert::model2Dto));
    }

    @SaCheckPermission("spu.view")
    @Operation(summary = "商品详情")
    @GetMapping("/{spuId}")
    public Result<SpuDTO> detail(@PathVariable("spuId") String spuId) {
        SpuModel spuModel = spuService.detaile(spuId);
        return Result.success(spuModel, spuCoreConvert::model2Dto);
    }

    @SaCheckPermission("spu.create")
    @Operation(summary = "商品创建")
    @PostMapping
    public Result<String> create(@RequestBody SpuDTO spuDTO) {
        String spuId = spuService.create(spuCoreConvert.dto2Model(spuDTO));
        return Result.success(spuId);
    }

    @SaCheckPermission("spu.update")
    @Operation(summary = "商品编辑")
    @PutMapping("/{spuId}")
    public Result<Boolean> edit(@PathVariable("spuId") String spuId, @RequestBody SpuDTO spuDTO) {
        spuDTO.setSpuId(spuId);
        return Result.success(spuService.edit(spuCoreConvert.dto2Model(spuDTO)));
    }

    @SaCheckPermission("spu.delete")
    @Operation(summary = "商品删除")
    @DeleteMapping("/{spuId}")
    public Result<Boolean> delete(@PathVariable("spuId") String spuId) {
        return Result.success(spuService.delete(spuId));
    }
}
