package tech.oldhorse.shop.service.sys.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConfigDTO {
    @Schema(description = "参数主键")
    private String configId;

    @Schema(description = "参数名称")
    private String configName;

    @Schema(description = "参数键名")
    private String configKey;

    @Schema(description = "参数键值")
    private String configValue;

    @Schema(description = "配置类型")
    private String configType;
}
