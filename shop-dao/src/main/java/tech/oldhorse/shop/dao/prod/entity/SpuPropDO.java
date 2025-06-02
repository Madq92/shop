package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * Spu属性关联
 * </p>
 *
 * @author mika
 * @since 2025-06-02
 */
@Getter
@Setter
@ToString
@TableName("prod_spu_prop")
public class SpuPropDO extends BaseEntity {

    /**
     * 字典ID
     */
    private String dictId;

    /**
     * 字典组ID
     */
    private String dictGroupId;

    /**
     * 字典组类型：UNIT,SPEC,LABEL
     */
    private String dictType;

    /**
     * SpuID
     */
    private String spuId;

    /**
     * skuId
     */
    private String skuId;

    /**
     * 排序值
     */
    private Integer sort;
}
