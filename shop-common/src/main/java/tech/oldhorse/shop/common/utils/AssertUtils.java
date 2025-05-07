package tech.oldhorse.shop.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import tech.oldhorse.shop.common.exception.BaseParamException;

import java.util.Collection;

public class AssertUtils {
    public AssertUtils() {
    }

    public static void notNull(Object obj) {
        if (obj == null) {
            throw new BaseParamException("资源不存在");
        }
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new BaseParamException(msg);
        }
    }

    public static void isNull(Object obj, String msg) {
        if (obj != null) {
            throw new BaseParamException(msg);
        }
    }

    public static void isNotEmpty(Collection<?> coll, String msg) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new BaseParamException(msg);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BaseParamException(message);
        }
    }


}
