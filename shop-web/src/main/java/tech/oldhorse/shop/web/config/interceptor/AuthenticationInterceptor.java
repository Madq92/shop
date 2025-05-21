package tech.oldhorse.shop.web.config.interceptor;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tech.oldhorse.shop.common.constants.CommonConstants;
import tech.oldhorse.shop.common.constants.ErrorCodeEnum;
import tech.oldhorse.shop.common.context.WebContext;
import tech.oldhorse.shop.common.context.WebContextHolder;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 登录用户Token验证、生成和权限验证的拦截器。
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final IdGeneratorWrapper idGenerator;

    public AuthenticationInterceptor(IdGeneratorWrapper idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String traceId = this.getTraceId(request);
        Method method = ((HandlerMethod) handler).getMethod();
        //如果没有登录则直接交给satoken注解去验证。
        if (!StpUtil.isLogin()) {
            // 如果此 Method 或其所属 Class 标注了 @SaIgnore，则忽略掉鉴权
            if (BooleanUtil.isTrue(SaStrategy.instance.isAnnotationPresent.apply(method, SaIgnore.class))) {
                return true;
            }

            Class<?> beanType = ((HandlerMethod) handler).getBeanType();
            if (!beanType.getPackageName().startsWith("tech.oldhorse.shop")) {
                // 跳过swagger的请求
                return true;
            }
            responseError(response, HttpServletResponse.SC_UNAUTHORIZED, Result.error(ErrorCodeEnum.UNAUTHORIZED_EXCEPTION, traceId));
            return false;
        }
        //对于已经登录的用户一定存在session对象。
        SaSession session = StpUtil.getTokenSession();
        if (session == null) {
            responseError(response, HttpServletResponse.SC_UNAUTHORIZED, Result.error(ErrorCodeEnum.UNAUTHORIZED_EXCEPTION, traceId));
            return false;
        }

        String userId = StpUtil.getLoginIdAsString();
        WebContext webContext = new WebContext();
        webContext.setUserId(userId);
        WebContextHolder.setWebContext(webContext);

//        try {
//            //执行基于stoken的注解鉴权。
//            SaStrategy.instance.checkMethodAnnotation.accept(method);
//        } catch (SaTokenException e) {
//            responseError(response, HttpServletResponse.SC_FORBIDDEN, Result.error(ErrorCodeEnum.NO_ACCESS_PERMISSION));
//            return false;
//        }
        return true;
    }

    private void responseError(HttpServletResponse response, int httpStatus, Result result) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setStatus(httpStatus);
        if (result != null) {
            out.print(JSONUtil.toJsonStr(result));
        }
        out.flush();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 这里需要空注解，否则sonar会不happy。
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        WebContextHolder.cleanThreadLocal();
    }

    private String getTraceId(HttpServletRequest request) {
        // 获取请求流水号。
        // 对于微服务系统，为了保证traceId在全调用链的唯一性，因此在网关的过滤器中创建了该值。
        String traceId = request.getHeader(CommonConstants.HTTP_HEADER_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = idGenerator.nextStringId();
        }
        return traceId;
    }
}
