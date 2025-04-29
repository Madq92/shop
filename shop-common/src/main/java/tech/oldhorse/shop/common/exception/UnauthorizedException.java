package tech.oldhorse.shop.common.exception;

import lombok.Getter;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;

@Getter
public class UnauthorizedException extends BaseParamException {

    public UnauthorizedException(String msg) {
        super(ErrorCodeEnum.UNAUTHORIZED_EXCEPTION, msg);
    }
}
