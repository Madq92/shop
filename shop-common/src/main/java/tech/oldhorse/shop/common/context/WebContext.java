package tech.oldhorse.shop.common.context;

import lombok.Getter;
import lombok.Setter;

/**
 * Web请求上下文
 */
@Getter
@Setter
public class WebContext {
    private String tenantId;

    private String userId;

    /**
     * 上下文初始化时间
     */
    private Long initTimestamp = System.currentTimeMillis();
}
