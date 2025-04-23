package tech.oldhorse.shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.oldhorse.shop.common.object.BaseEntity;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author mika
 * @since 2025-04-23
 */
@Getter
@Setter
@ToString
@TableName("config")
public class ConfigDO extends BaseEntity {

    /**
     * 参数主键
     */
    private String configId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 配置类型（SYS 系统参数）
     */
    private String configType;

    /**
     * 状态
     */
    private String status;
}
