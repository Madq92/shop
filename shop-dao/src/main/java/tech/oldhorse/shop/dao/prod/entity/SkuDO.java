package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * SKU
 * </p>
 *
 * @author mika
 * @since 2025-06-02
 */
@Getter
@Setter
@ToString
@TableName("prod_sku")
public class SkuDO extends BaseEntity {

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
    private String weightFlag;

    /**
     * 默认重量(克)
     */
    private Integer defaultWeight;

    /**
     * 状态
     */
    private String status;
}
