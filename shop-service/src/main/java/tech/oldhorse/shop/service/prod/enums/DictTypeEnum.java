package tech.oldhorse.shop.service.prod.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DictTypeEnum {
    UNIT("单位"),
    SPEC("规格"),
    LABEL("标签");
    private String desc;
}
