package tech.oldhorse.shop.service.prod.condition;

import lombok.Data;

@Data
public class CategoryCondition {
    private String nameLike;
    private String parentId;
}
