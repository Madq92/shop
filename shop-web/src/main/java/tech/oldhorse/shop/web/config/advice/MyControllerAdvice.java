package tech.oldhorse.shop.web.config.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;
import tech.oldhorse.shop.common.exception.BaseParamException;
import tech.oldhorse.shop.common.exception.BaseServiceException;
import tech.oldhorse.shop.common.exception.ForbiddenException;
import tech.oldhorse.shop.common.exception.UnauthorizedException;
import tech.oldhorse.shop.common.object.Result;

import java.time.LocalDateTime;

/**
 * Controller的环绕拦截类。
 */
@Slf4j
@ControllerAdvice("tech.oldhorse.shop.web")
public class MyControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeConverter());
    }

    /**
     * 通用异常处理方法。
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result<Void>> exceptionHandle(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception from URL [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error(ErrorCodeEnum.SERVER_INTERNAL_ERROR.name(), ex.getMessage()));
    }

    /**
     * 400异常
     */
    @ExceptionHandler(value = BaseParamException.class)
    public ResponseEntity<Result<Void>> baseParamExceptionHandle(BaseParamException ex, HttpServletRequest request) {
        log.error("BaseParamException exception from URL [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(ex.getErrorCodeEnum().name(), ex.getMessage()));
    }

    /**
     * 401异常
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Result<Void>> unauthorizedExceptionHandle(UnauthorizedException ex, HttpServletRequest request) {
        log.error("UnauthorizedException exception from URL [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.error(ex.getErrorCodeEnum().name(), ex.getMessage()));
    }

    /**
     * 403异常
     */
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Result<Void>> forbiddenExceptionHandle(ForbiddenException ex, HttpServletRequest request) {
        log.error("ForbiddenException exception from URL [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.error(ex.getErrorCodeEnum().name(), ex.getMessage()));
    }

    /**
     * 500异常
     */
    @ExceptionHandler(value = BaseServiceException.class)
    public ResponseEntity<Result<Void>> baseServiceExceptionHandle(BaseServiceException ex, HttpServletRequest request) {
        log.error("BaseServiceException exception from URL [{}]", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error(ex.getErrorCodeEnum().name(), ex.getMessage()));
    }
}
