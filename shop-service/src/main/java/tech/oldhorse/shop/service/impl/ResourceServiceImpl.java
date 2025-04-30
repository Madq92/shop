package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.repository.ResourceRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.object.model.ResourceModel;

import java.util.List;
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceCoreConvert resourceCoreConvert;
    @Autowired
    IdGeneratorWrapper idGeneratorWrapper;
    @Override
    public String create(ResourceModel resourceModel) {
        return "";
    }

    @Override
    public Boolean edit(ResourceModel resourceModel) {
        return null;
    }

    @Override
    public Boolean delete(String resourceId) {
        return null;
    }

    @Override
    public ResourceModel getByResourceId(String resourceId) {
        return null;
    }

    @Override
    public List<ResourceModel> listByCondition(ResourceCondition condition) {
        return List.of();
    }
}
