package tech.oldhorse.shop.service.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {
    MENU("菜单"),
    API("接口");
    private String desc;
}
