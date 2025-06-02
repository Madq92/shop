package tech.oldhorse.shop.service.prod.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.oldhorse.shop.common.object.PageParam;
import tech.oldhorse.shop.service.prod.enums.ProdStatusEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpuCondition extends PageParam {
    private String nameLike;
    private ProdStatusEnum status;

    public SpuCondition() {

    }

    public SpuCondition(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }
}
