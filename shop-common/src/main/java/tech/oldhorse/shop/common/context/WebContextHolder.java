package tech.oldhorse.shop.common.context;


import org.springframework.stereotype.Component;

@Component
public class WebContextHolder {
    private static final ThreadLocal<WebContext> WEB_CONTEX = new ThreadLocal<>();

    public static WebContext getWebContext() {
        return WEB_CONTEX.get();
    }

    public static void setWebContext(WebContext context) {
        WEB_CONTEX.set(context);
    }

    public static void cleanThreadLocal() {
        WEB_CONTEX.remove();
    }

    public static String getTenantId() {
        WebContext webContext = getWebContext();
        if (null != webContext) {
            return webContext.getTenantId();
        }
        return null;
    }

    public static String getUserId() {
        WebContext webContext = getWebContext();
        if (null != webContext) {
            return webContext.getUserId();
        }
        return null;
    }
}
