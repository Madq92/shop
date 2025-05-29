package tech.oldhorse.shop.service.sys;

import tech.oldhorse.shop.service.sys.condition.ResourceCondition;
import tech.oldhorse.shop.service.sys.object.model.ResourceModel;

import java.util.List;

public interface ResourceService {
    String create(ResourceModel resourceModel);

    Boolean edit(ResourceModel resourceModel);

    Boolean delete(String resourceId);

    ResourceModel getByResourceId(String resourceId);

    List<ResourceModel> listByCondition(ResourceCondition condition);
}
