package tech.oldhorse.shop.service.prod.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;
import tech.oldhorse.shop.service.prod.enums.SpuTypeEnum;
import tech.oldhorse.shop.service.prod.enums.YesOrNoEnum;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;
import tech.oldhorse.shop.service.prod.object.model.DictGroupModel;

import java.util.List;

@Data
public class SpuDTO {
    @Schema(description = "spuId")
    private String spuId;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "编号，方便内部管理")
    private String code;

    @Schema(description = "商品类型：SINGLE,MULTI")
    private SpuTypeEnum type;

    @Schema(description = "单位ID")
    private String unitId;

    @Schema(description = "单位Name")
    private String unitName;

    @Schema(description = "分类ID")
    private String categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "父分类ID")
    private String parentCategoryId;

    @Schema(description = "父分类名称")
    private String parentCategoryName;

    @Schema(description = "称重商品标识：Y-是,N-否")
    private YesOrNoEnum weightFlag;

    @Schema(description = "图片,json")
    private String imgUrl;

    @Schema(description = "描述")
    private String spuDesc;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private ProdStatusEnum status;

    @Schema(description = "分类")
    private CategoryModel category;

    @Schema(description = "sku列表")
    private List<SkuDTO> skus;

    @Schema(description = "属性列表")
    private List<DictGroupModel> props;
}
