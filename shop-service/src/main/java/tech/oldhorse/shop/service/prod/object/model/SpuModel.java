package tech.oldhorse.shop.service.prod.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;
import tech.oldhorse.shop.service.prod.enums.SpuTypeEnum;
import tech.oldhorse.shop.service.prod.enums.YesOrNoEnum;

import java.util.List;

@Data
public class SpuModel extends BaseModel {

    /**
     * spuId
     */
    private String spuId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 编号，方便内部管理
     */
    private String code;

    /**
     * 商品类型：SINGLE,MULTI
     */
    private SpuTypeEnum type;

    /**
     * 单位ID
     */
    private String unitId;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 称重商品标识：Y-是,N-否
     */
    private YesOrNoEnum weightFlag;

    /**
     * 图片,json
     */
    private String imgUrl;

    /**
     * 描述
     */
    private String spuDesc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private ProdStatusEnum status;

    /**
     * 分类
     */
    private CategoryModel category;

    /**
     * sku列表
     */
    private List<SkuModel> skus;

    /**
     * 属性列表
     */
    private List<DictGroupModel> props;
}
