package tech.oldhorse.shop.service.prod.condition;

import lombok.Data;
import tech.oldhorse.shop.service.prod.enums.DictTypeEnum;

import java.util.List;

@Data
public class DictCondition {
    private String nameLike;
    private String groupName;
    private String name;
    private DictTypeEnum type;
    private List<String> dictIdList;
    private List<String> dictGroupIdList;

}
