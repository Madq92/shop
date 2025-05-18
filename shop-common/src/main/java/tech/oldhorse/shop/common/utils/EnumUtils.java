package tech.oldhorse.shop.common.utils;

public class EnumUtils {
    public static <T extends Enum<T>> T getByName(Class<T> enumClass, String name) {
        for (T value : enumClass.getEnumConstants()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
