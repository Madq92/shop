package tech.oldhorse.shop.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.oldhorse.shop.dao.plugins.BaseDataInterceptor;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public BaseDataInterceptor baseDataInterceptor() {
        return new BaseDataInterceptor();
    }
}
