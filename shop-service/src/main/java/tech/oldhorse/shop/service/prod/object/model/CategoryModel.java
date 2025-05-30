package tech.oldhorse.shop.service.prod.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;


@Data
public class CategoryModel extends BaseModel {

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
