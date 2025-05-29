package tech.oldhorse.shop.service.sys.condition;

import lombok.Data;

@Data
public class ConfigCondition {
    private String configNameLike;
    private String configKey;
    private String configType;
}
