package tech.oldhorse.shop.service.prod.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.prod.enums.DictTypeEnum;

import java.util.List;

@Data
public class DictGroupModel extends BaseModel {

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
    private DictTypeEnum type;

    private List<DictModel> dictDetails;
}
