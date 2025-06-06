package tech.oldhorse.shop.common.exception;

import lombok.Getter;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;

/**
 * 服务端异常
 */
@Getter
public class BaseServiceException extends RuntimeException {
    private final ErrorCodeEnum errorCodeEnum;

    public BaseServiceException(String msg) {
        super(msg);
        this.errorCodeEnum = ErrorCodeEnum.SERVER_INTERNAL_ERROR;
    }

    public BaseServiceException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(msg);
        this.errorCodeEnum = errorCodeEnum;
    }

    public BaseServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.errorCodeEnum = ErrorCodeEnum.SERVER_INTERNAL_ERROR;
    }
}
