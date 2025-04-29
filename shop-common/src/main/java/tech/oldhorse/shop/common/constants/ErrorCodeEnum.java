package tech.oldhorse.shop.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回应答中的错误代码和错误信息
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    /**
     * 没有错误
     */
    NO_ERROR("没有错误"),

    SERVER_INTERNAL_ERROR("服务器内部错误，请联系管理员！"),

    PARAM_EXCEPTION("请求参数错误！"),
    UNAUTHORIZED_EXCEPTION("当前用户尚未登录或登录已超时，请重新登录！"),
    NO_ACCESS_PERMISSION("当前用户没有访问权限！"),
    INVALID_TENANT_STATUS("当前租户为不可用状态，请刷新后重试！"),

    FAILED_TO_INVOKE_THIRDPARTY_URL("调用第三方接口失败！"),

    ;

    private final String errorMessage;
}
