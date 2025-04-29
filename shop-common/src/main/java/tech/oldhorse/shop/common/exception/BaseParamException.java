package tech.oldhorse.shop.common.exception;

import lombok.Getter;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;

/**
 * 参数异常
 */
@Getter
public class BaseParamException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;

    public BaseParamException(String msg) {
        super(msg);
        this.errorCodeEnum = ErrorCodeEnum.PARAM_EXCEPTION;
    }

    public BaseParamException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(msg);
        this.errorCodeEnum = errorCodeEnum;
    }
}
