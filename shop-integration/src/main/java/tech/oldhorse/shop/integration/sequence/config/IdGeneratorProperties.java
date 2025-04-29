package tech.oldhorse.shop.integration.sequence.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * common-sequence模块的配置类。
 *
 * @author Jerry
 * @date 2024-07-02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "common-sequence")
public class IdGeneratorProperties {

    /**
     * 基础版生成器所需的WorkNode参数值。仅当advanceIdGenerator为false时生效。
     */
    private Integer snowflakeWorkNode = 1;
}
