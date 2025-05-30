package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 字典组
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Getter
@Setter
@ToString
@TableName("prod_dict_group")
public class DictGroupDO extends BaseEntity {

    /**
     * 字典组ID
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
