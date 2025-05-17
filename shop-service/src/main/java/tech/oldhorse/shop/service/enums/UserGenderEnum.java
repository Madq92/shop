package tech.oldhorse.shop.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGenderEnum {
    MALE("男"),
    FEMALE("女"),
    NONE("未知");
    private String desc;

    public static UserGenderEnum getByName(String status) {
        for (UserGenderEnum value : values()) {
            if (value.name().equals(status)) {
                return value;
            }
        }
        return null;
    }
}
