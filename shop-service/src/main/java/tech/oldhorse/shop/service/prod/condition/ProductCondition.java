package tech.oldhorse.shop.service.prod.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductCondition extends PageParam {
    private String nameLike;

    public ProductCondition() {

    }

    public ProductCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
