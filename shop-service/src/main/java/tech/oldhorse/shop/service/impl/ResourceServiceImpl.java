package tech.oldhorse.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.entity.ResourceDO;
import tech.oldhorse.shop.dao.entity.RoleResourceDO;
import tech.oldhorse.shop.dao.repository.ResourceRepository;
import tech.oldhorse.shop.dao.repository.RoleResourceRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.ResourceService;
import tech.oldhorse.shop.service.condition.ResourceCondition;
import tech.oldhorse.shop.service.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.object.model.ResourceModel;

import java.util.List;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {
    private final static String EXIST_SQL = "SELECT 1 FROM role_resource WHERE role_resource.resource_id = resource.resource_id and role_id IN (%s)";
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceCoreConvert resourceCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;
    @Autowired
    RoleResourceRepository roleResourceRepository;

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
        AssertUtils.notNull(resourceInDb);

        ResourceDO resourceDO = resourceCoreConvert.model2Do(resourceModel);
        resourceDO.setId(resourceInDb.getId());
        return resourceRepository.updateById(resourceDO);
    }

    @Override
    public Boolean delete(String resourceId) {
        ResourceModel resourceInDb = getByResourceId(resourceId);
        AssertUtils.notNull(resourceInDb);

        RoleResourceDO one = roleResourceRepository.lambdaQuery().eq(RoleResourceDO::getResourceId, resourceId).one();
        AssertUtils.isNull(one, "资源被引用，无法删除");

        ResourceDO resourceDO = new ResourceDO();
        resourceDO.setId(resourceInDb.getId());
        resourceDO.setDeletedFlag(true);
        return resourceRepository.updateById(resourceDO);
    }

    @Override
    public ResourceModel getByResourceId(String resourceId) {
        ResourceDO one = resourceRepository.lambdaQuery().eq(ResourceDO::getDeletedFlag, false).eq(ResourceDO::getResourceId, resourceId).one();
        AssertUtils.notNull(one);
        return resourceCoreConvert.do2Model(one);
    }

    @Override
    public List<ResourceModel> listByCondition(ResourceCondition condition) {
        String existSql = null;
        if (CollectionUtils.isNotEmpty(condition.getRoleIds())) {
            existSql = String.format(EXIST_SQL, String.join(",", condition.getRoleIds()));
        }
        List<ResourceDO> list = resourceRepository.lambdaQuery()
                .eq(ResourceDO::getDeletedFlag, false)
                .in(CollectionUtils.isNotEmpty(condition.getResourceIds()), ResourceDO::getResourceId, condition.getResourceIds())
                .exists(null != existSql, existSql)
                .list();
        return resourceCoreConvert.doList2ModelList(list);
    }
}
