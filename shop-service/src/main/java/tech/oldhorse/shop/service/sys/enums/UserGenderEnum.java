package tech.oldhorse.shop.service.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGenderEnum {
    MALE("男"),
    FEMALE("女"),
    NONE("无");
    private String desc;
}
