package tech.oldhorse.shop.service.prod.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.oldhorse.shop.service.prod.enums.DictTypeEnum;

@Data
public class DictDTO{
    @Schema(description = "字典ID")
    private String dictId;

    @Schema(description = "字典组ID")
    private String dictGroupId;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典组类型：UNIT,SPEC,LABEL")
    private DictTypeEnum type;
}
