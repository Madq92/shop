package tech.oldhorse.shop.web.interceptor;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.nio.charset.StandardCharsets;

/**
 * 这里主要配置Web的各种过滤器和监听器等Servlet容器组件。
 */
@Configuration
public class FilterConfig {

    /**
     * 配置Ajax跨域过滤器。
     */
    @Bean
    public CorsFilter corsFilterRegistration() {
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        configSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(configSource);
    }

    @Bean
    public FilterRegistrationBean<Filter> characterEncodingFilterRegistration() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(
                new org.springframework.web.filter.CharacterEncodingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("encoding", StandardCharsets.UTF_8.name());
        // forceEncoding强制response也被编码，另外即使request中已经设置encoding，forceEncoding也会重新设置
        filterRegistrationBean.addInitParameter("forceEncoding", "true");
        filterRegistrationBean.setAsyncSupported(true);
        return filterRegistrationBean;
    }
}
