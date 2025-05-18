package tech.oldhorse.shop.service.condition;

import lombok.Data;
import tech.oldhorse.shop.service.enums.ResourceTypeEnum;

import java.util.List;

@Data
public class ResourceCondition {
    private List<String> resourceIds;
    private List<String> roleIds;
    private ResourceTypeEnum type;
}
