package tech.oldhorse.shop.service;

import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.object.model.ResourceModel;

import java.util.List;

public interface ResourceService {
    String create(ResourceModel resourceModel);

    Boolean edit(ResourceModel resourceModel);

    Boolean delete(String resourceId);

    ResourceModel getByResourceId(String resourceId);

    List<ResourceModel> listByCondition(ResourceCondition condition);
}
