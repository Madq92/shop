package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Getter
@Setter
@ToString
@TableName("prod_dict")
public class DictDO extends BaseEntity {

    /**
     * 字典ID
     */
    private String dictId;

    /**
     * 字典ID
     */
    private String dictGroupId;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典组类型：UNIT,SPEC,LABEL
     */
    private String type;
}
