package tech.oldhorse.shop.dao.prod.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Getter
@Setter
@ToString
@TableName("prod_category")
public class CategoryDO extends BaseEntity {

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;
}
