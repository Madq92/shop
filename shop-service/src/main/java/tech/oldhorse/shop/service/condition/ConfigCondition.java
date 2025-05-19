package tech.oldhorse.shop.service.condition;

import lombok.Data;

@Data
public class ConfigCondition {
    private String configNameLike;
    private String configKey;
    private String configType;
}
