package tech.oldhorse.shop.service.prod.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;
import tech.oldhorse.shop.service.prod.enums.YesOrNoEnum;

import java.util.List;

@Data
public class SkuModel extends BaseModel {

    /**
     * skuId
     */
    private String skuId;

    /**
     * spuId
     */
    private String spuId;

    /**
     * 编号，方便内部管理
     */
    private String code;

    /**
     * 销售价格
     */
    private Long sellPrice;

    /**
     * 销售价格1
     */
    private Long sellPrice1;

    /**
     * 销售价格2
     */
    private Long sellPrice2;

    /**
     * 销售价格3
     */
    private Long sellPrice3;

    /**
     * 图片,json
     */
    private String imgUrl;

    /**
     * 称重商品标识：Y-是,N-否
     */
    private YesOrNoEnum weightFlag;

    /**
     * 默认重量(克)
     */
    private Integer defaultWeight;

    /**
     * 状态
     */
    private ProdStatusEnum status;

    /**
     * 规格
     */
    private  List<DictGroupModel> specs;
}
