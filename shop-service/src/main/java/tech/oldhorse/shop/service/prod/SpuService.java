package tech.oldhorse.shop.service.prod;

import tech.oldhorse.shop.common.object.PageData;
import tech.oldhorse.shop.service.prod.condition.ProductCondition;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

import java.util.List;

public interface SpuService {
    String create(SpuModel spuModel);

    Boolean edit(SpuModel spuModel);

    Boolean delete(String spuId);

    /**
     * 根据spuId查询
     *
     * @param spuId
     * @return
     */
    SpuModel getBySpuId(String spuId);

    /**
     * 详情,包含所有
     *
     * @param spuId
     * @return
     */
    SpuModel detaile(String spuId);

    PageData<SpuModel> pageByCondition(ProductCondition condition);

    List<SpuModel> listByCondition(ProductCondition condition);
}
