package tech.oldhorse.shop.common.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;
import tech.oldhorse.shop.common.exception.BaseParamException;
import tech.oldhorse.shop.common.exception.BaseServiceException;
import tech.oldhorse.shop.common.exception.ForbiddenException;
import tech.oldhorse.shop.common.exception.UnauthorizedException;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.ContextUtil;

/**
 * 业务层异常处理
 */
@Slf4j
@RestControllerAdvice("tech.oldhorse")
public class MyExceptionHandler {

    /**
     * 通用异常处理方法。
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Void> exceptionHandle(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception from URL [{}]", request.getRequestURI(), ex);
        ContextUtil.getHttpResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.error(ErrorCodeEnum.SERVER_INTERNAL_ERROR.name(), ex.getMessage());
    }

    /**
     * 400异常
     */
    @ExceptionHandler(value = BaseParamException.class)
    public Result<Void> baseParamExceptionHandle(BaseParamException ex, HttpServletRequest request) {
        log.error("BaseParamException exception from URL [{}]", request.getRequestURI(), ex);
        ContextUtil.getHttpResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return Result.error(ex.getErrorCodeEnum().name(), ex.getMessage());
    }

    /**
     * 401异常
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<Void> unauthorizedExceptionHandle(UnauthorizedException ex, HttpServletRequest request) {
        log.error("UnauthorizedException exception from URL [{}]", request.getRequestURI(), ex);
        ContextUtil.getHttpResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return Result.error(ex.getErrorCodeEnum().name(), ex.getMessage());
    }

    /**
     * 403异常
     */
    @ExceptionHandler(value = ForbiddenException.class)
    public Result<Void> forbiddenExceptionHandle(ForbiddenException ex, HttpServletRequest request) {
        log.error("ForbiddenException exception from URL [{}]", request.getRequestURI(), ex);
        ContextUtil.getHttpResponse().setStatus(HttpServletResponse.SC_FORBIDDEN);
        return Result.error(ex.getErrorCodeEnum().name(), ex.getMessage());
    }

    /**
     * 500异常
     */
    @ExceptionHandler(value = BaseServiceException.class)
    public Result<Void> baseServiceExceptionHandle(BaseServiceException ex, HttpServletRequest request) {
        log.error("BaseServiceException exception from URL [{}]", request.getRequestURI(), ex);
        ContextUtil.getHttpResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.error(ex.getErrorCodeEnum().name(), ex.getMessage());
    }
}
