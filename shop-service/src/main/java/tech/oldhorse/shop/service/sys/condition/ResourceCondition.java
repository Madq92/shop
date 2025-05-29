package tech.oldhorse.shop.service.sys.condition;

import lombok.Data;
import tech.oldhorse.shop.service.sys.enums.ResourceTypeEnum;

import java.util.List;

@Data
public class ResourceCondition {
    private List<String> resourceIds;
    private List<String> roleIds;
    private ResourceTypeEnum type;
}
