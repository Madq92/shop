package tech.oldhorse.shop.service.prod.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProdStatusEnum {
    ENABLE("启用"),
    DISABLE("停用");
    private String desc;
}
