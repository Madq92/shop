package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * SPU
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Getter
@Setter
@ToString
@TableName("prod_spu")
public class SpuDO extends BaseEntity {

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
    private String type;

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
    private String weightFlag;

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
    private String status;
}
