package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.dao.entity.ResourceDO;
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
    IdGeneratorWrapper idGenerator;

    @Override
    public String create(ResourceModel resourceModel) {
        String resourceId = idGenerator.nextStringId();
        resourceModel.setResourceId(resourceId);

        ResourceDO resourceDO = resourceCoreConvert.model2Do(resourceModel);
        resourceRepository.save(resourceDO);
        return resourceId;
    }

    @Override
    public Boolean edit(ResourceModel resourceModel) {
        ResourceModel resourceInDb = getByResourceId(resourceModel.getResourceId());

        ResourceDO resourceDO = resourceCoreConvert.model2Do(resourceModel);
        resourceDO.setId(resourceInDb.getId());
        return resourceRepository.updateById(resourceDO);
    }

    @Override
    public Boolean delete(String resourceId) {
        ResourceModel resourceInDb = getByResourceId(resourceId);


        ResourceDO resourceDO = new ResourceDO();
        resourceDO.setId(resourceInDb.getId());
        resourceDO.setDeletedFlag(true);
        return resourceRepository.updateById(resourceDO);
    }

    @Override
    public ResourceModel getByResourceId(String resourceId) {
        ResourceDO one = resourceRepository.lambdaQuery().eq(ResourceDO::getDeletedFlag, false).eq(ResourceDO::getResourceId, resourceId).one();
        return resourceCoreConvert.do2Model(one);
    }

    @Override
    public List<ResourceModel> listByCondition(ResourceCondition condition) {
        List<ResourceDO> list = resourceRepository.lambdaQuery().eq(ResourceDO::getDeletedFlag, false).list();
        return resourceCoreConvert.doList2ModelList(list);
    }
}
