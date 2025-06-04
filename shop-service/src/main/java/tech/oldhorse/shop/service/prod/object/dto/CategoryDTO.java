package tech.oldhorse.shop.service.prod.object.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class CategoryDTO {
    @Schema(description = "分类ID")
    private String categoryId;

    @Schema(description = "父节点")
    private String parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;
}
