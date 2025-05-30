package tech.oldhorse.shop.service.prod.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpuTypeEnum {
    SINGLE("单规格"),
    MULTI("多规格");
    private String desc;
}
