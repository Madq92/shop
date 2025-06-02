package tech.oldhorse.shop.service.prod;

import tech.oldhorse.shop.service.prod.condition.DictCondition;
import tech.oldhorse.shop.service.prod.object.model.DictGroupModel;
import tech.oldhorse.shop.service.prod.object.model.DictModel;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

import java.util.List;

public interface DictService {
    String createGroup(DictGroupModel dictGroupModel);

    Boolean editGroup(DictGroupModel dictGroupModel);

    Boolean deleteGroup(String dictGroupId);

    DictGroupModel getByDictGroupId(String dictGroupId);

    DictGroupModel groupDetaile(String dictGroupId);


    String createDetail(DictModel dictModel);

    Boolean editDetail(DictModel dictModel);

    Boolean deleteDetail(String dictId);

    DictModel getByDictId(String dictId);

    List<DictModel> listDetailByIds(List<String> dictIds);

    List<DictGroupModel> listByCondition(DictCondition condition);
}
