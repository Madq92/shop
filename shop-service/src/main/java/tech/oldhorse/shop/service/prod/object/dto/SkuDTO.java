package tech.oldhorse.shop.service.prod.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;
import tech.oldhorse.shop.service.prod.enums.YesOrNoEnum;

import java.util.List;

@Data
public class SkuDTO {
    @Schema(description = "skuId")
    private String skuId;

    @Schema(description = "spuId")
    private String spuId;

    @Schema(description = "编号，方便内部管理")
    private String code;

    @Schema(description = "销售价格")
    private Long sellPrice;

    @Schema(description = "销售价格1")
    private Long sellPrice1;

    @Schema(description = "销售价格2")
    private Long sellPrice2;

    @Schema(description = "销售价格3")
    private Long sellPrice3;

    @Schema(description = "图片,json")
    private String imgUrl;

    @Schema(description = "称重商品标识：Y-是,N-否")
    private YesOrNoEnum weightFlag;

    @Schema(description = "默认重量(克)")
    private Integer defaultWeight;

    @Schema(description = "状态")
    private ProdStatusEnum status;

    @Schema(description = "规格")
    private List<DictGroupDTO> specs;
}
