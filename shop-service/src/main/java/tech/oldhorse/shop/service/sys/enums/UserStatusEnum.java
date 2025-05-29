package tech.oldhorse.shop.service.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    ENABLE("启用"),
    DISABLE("停用");
    private String desc;
}
