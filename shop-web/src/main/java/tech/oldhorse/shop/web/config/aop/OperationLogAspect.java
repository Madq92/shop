package tech.oldhorse.shop.web.config.aop;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.oldhorse.shop.common.constants.CommonConstants;
import tech.oldhorse.shop.common.object.Result;
import tech.oldhorse.shop.common.utils.ContextUtil;
import tech.oldhorse.shop.common.utils.IpUtil;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.OperLogService;
import tech.oldhorse.shop.service.object.model.OperLogModel;
import tech.oldhorse.shop.service.object.model.UserModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 操作日志记录处理AOP对象。
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class OperationLogAspect {
    /**
     * 错误信息、请求参数和应答结果字符串的最大长度。
     */
    private static final int MAX_LENGTH = 2048;
    @Value("${spring.application.name}")
    private String serviceName;
    @Autowired
    private OperLogService operLogService;
    @Autowired
    private IdGeneratorWrapper idGenerator;

    /**
     * 所有controller方法。
     */
    @Pointcut("execution(public * tech.oldhorse.shop.web.controller..*(..))")
    public void operationLogPointCut() {
        // 空注释，避免sonar警告
    }

    @Around("operationLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 计时。
        long start = System.currentTimeMillis();
        HttpServletRequest request = ContextUtil.getHttpRequest();
        HttpServletResponse response = ContextUtil.getHttpResponse();
        String traceId = this.getTraceId(request);
        request.setAttribute(CommonConstants.HTTP_HEADER_TRACE_ID, traceId);
        // 将流水号通过应答头返回给前端，便于问题精确定位。
        response.setHeader(CommonConstants.HTTP_HEADER_TRACE_ID, traceId);
        MDC.put(CommonConstants.HTTP_HEADER_TRACE_ID, traceId);

        UserModel currentUser = null;
        if (StpUtil.isLogin()) {
            SaSession session = StpUtil.getSession();
            currentUser = (UserModel) session.get(CommonConstants.SESSION_USER_KEY);
        }
        Operation operationAnnotation = getMethodAnnotation(joinPoint, Operation.class);
        log.info("==> 开始请求，url={}", request.getRequestURI());
        Object resp;
        try {
            // 调用原来的方法
            resp = joinPoint.proceed();
            if (resp instanceof Result<?> result) {
                result.setTraceId(traceId);
                return result;
            }
        } catch (Exception e) {
            log.error("==>  请求报错，url={}", request.getRequestURI(), e);
            throw e;
        } finally {
            Long elapse = System.currentTimeMillis() - start;
            log.info("<== 结束请求，url={}, elapse={}ms", request.getRequestURI(), elapse);
            // 记录日志
            OperLogModel operLogModel = new OperLogModel();
            operLogModel.setTraceId(traceId);
            operLogModel.setOperUrl(request.getRequestURI());
            operLogModel.setRequestMethod(request.getMethod());
            operLogModel.setOperIp(IpUtil.getRemoteIpAddress(request));
            operLogModel.setCostTime(elapse);
            operLogModel.setOperTime(LocalDateTime.now());
            if (operationAnnotation != null) {
                operLogModel.setTitle(operationAnnotation.summary());
            }
            if (currentUser != null) {
                operLogModel.setOperUserId(currentUser.getUserId());
                operLogModel.setOperUseName(currentUser.getName());
                operLogModel.setTenantId(currentUser.getTenantId());
            }
            operLogService.create(operLogModel);
            MDC.remove(CommonConstants.HTTP_HEADER_TRACE_ID);
        }
        return resp;
    }

    private <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> annotationClazz) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(annotationClazz);
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
