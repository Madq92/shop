package tech.oldhorse.shop.common.object;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;

import java.util.function.Function;

@Slf4j
@Data
public class Result<T> {

    /**
     * 为了优化性能，所有没有携带数据的正确结果，均可用该对象表示。
     */
    private static final Result<Void> OK = new Result<>();
    /**
     * 是否成功标记。
     */
    private boolean success = true;
    /**
     * 错误码。
     */
    private String errorCode = "SUCCESS";
    /**
     * 错误信息描述。
     */
    private String errorMessage = "";
    /**
     * 链路ID
     */
    private String traceId = "";
    /**
     * 实际数据。
     */
    private T data = null;

    private Result() {
    }


    private Result(String errorCode, String errorMessage) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Result(String errorCode, String errorMessage, String traceId) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.traceId = traceId;
    }

    /**
     * 根据参数errorCode是否为空，判断创建成功对象还是错误对象。
     * 如果返回错误对象，errorCode 和 errorMessage 分别取自于参数 errorCode 和参数 errorMessage。
     *
     * @param errorCode    自定义的错误码。
     * @param errorMessage 自定义的错误信息。
     * @return 返回创建的ResponseResult实例对象。
     */
    public static Result<Void> create(String errorCode, String errorMessage) {
        return errorCode == null ? success() : error(errorCode, errorMessage);
    }

    /**
     * 创建成功对象。
     * 如果需要绑定返回数据，可以在实例化后调用setDataObject方法。
     *
     * @return 返回创建的ResponseResult实例对象。
     */
    public static Result<Void> success() {
        return OK;
    }

    /**
     * 创建带有返回数据的成功对象。
     *
     * @param data 返回的数据对象。
     * @return 返回创建的ResponseResult实例对象。
     */
    public static <T> Result<T> success(T data) {
        Result<T> resp = new Result<>();
        resp.data = data;
        return resp;
    }

    /**
     * 创建错误对象。
     * 如果返回错误对象，errorCode 和 errorMessage 分别取自于参数 errorCode 和参数 errorMessage。
     *
     * @param errorCode    自定义的错误码。
     * @param errorMessage 自定义的错误信息。
     * @return 返回创建的ResponseResult实例对象。
     */
    public static <T> Result<T> error(String errorCode, String errorMessage) {
        return new Result<>(errorCode, errorMessage);
    }

    public static <T> Result<T> error(String errorCode, String errorMessage, String traceId) {
        return new Result<>(errorCode, errorMessage, traceId);
    }

    public static <T> Result<T> error(ErrorCodeEnum errorCodeEnum) {
        return new Result<>(errorCodeEnum.name(), errorCodeEnum.getErrorMessage());
    }

    public static <T> Result<T> error(ErrorCodeEnum errorCodeEnum, String traceId) {
        Result<T> result = new Result<T>(errorCodeEnum.name(), errorCodeEnum.getErrorMessage());
        result.setTraceId(traceId);
        return result;
    }

    public static <T, M> Result<T> success(M model, Function<M, T> mapping) {
        return success(mapping.apply(model));
    }
}
