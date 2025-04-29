package tech.oldhorse.shop.common.exception;

import lombok.Getter;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;

@Getter
public class ForbiddenException extends BaseParamException {
    private ErrorCodeEnum errorCodeEnum;

    public ForbiddenException(String msg) {
        super(ErrorCodeEnum.NO_ACCESS_PERMISSION, msg);
    }
}
