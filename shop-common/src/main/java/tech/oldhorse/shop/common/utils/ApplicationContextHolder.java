package tech.oldhorse.shop.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tech.oldhorse.shop.common.exception.BaseServiceException;

import java.util.Collection;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        doSetApplicationContext(applicationContext);
    }

    /**
     * 获取应用上下文对象。
     */
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * 根据BeanName，获取Bean对象。
     *
     * @param beanName Bean名称。
     * @param <T> 返回的Bean类型。
     * @return Bean对象。
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据Bean的ClassType，获取Bean对象。
     *
     * @param beanType Bean的Class类型。
     * @param <T> 返回的Bean类型。
     * @return Bean对象。
     */
    public static <T> T getBean(Class<T> beanType) {
        assertApplicationContext();
        return applicationContext.getBean(beanType);
    }

    /**
     * 根据Bean的ClassType，获取Bean对象列表。
     *
     * @param beanType Bean的Class类型。
     * @param <T>      返回的Bean类型。
     * @return Bean对象列表。
     */
    public static <T> Collection<T> getBeanListOfType(Class<T> beanType) {
        assertApplicationContext();
        return applicationContext.getBeansOfType(beanType).values();
    }

    private static void assertApplicationContext() {
        if (ApplicationContextHolder.applicationContext == null) {
            throw new BaseServiceException("applicaitonContext属性为null,请检查是否注入了ApplicationContextHolder!");
        }
    }

    private static void doSetApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}