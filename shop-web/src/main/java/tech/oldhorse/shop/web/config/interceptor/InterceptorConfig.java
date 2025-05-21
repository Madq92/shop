package tech.oldhorse.shop.web.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(idGenerator)).addPathPatterns("/**");
    }
}
