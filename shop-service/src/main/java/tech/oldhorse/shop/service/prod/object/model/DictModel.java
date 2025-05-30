package tech.oldhorse.shop.service.prod.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;
import tech.oldhorse.shop.service.prod.enums.DictTypeEnum;

@Data
public class DictModel extends BaseModel {

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
    private DictTypeEnum type;
}
