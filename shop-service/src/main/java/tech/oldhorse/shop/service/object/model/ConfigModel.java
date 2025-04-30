package tech.oldhorse.shop.service.object.model;

import lombok.Data;
import tech.oldhorse.shop.common.object.BaseModel;

@Data
public class ConfigModel extends BaseModel {
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
}
