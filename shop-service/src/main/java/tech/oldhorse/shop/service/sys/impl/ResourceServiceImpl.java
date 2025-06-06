package tech.oldhorse.shop.service.sys.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.sys.entity.ResourceDO;
import tech.oldhorse.shop.dao.sys.entity.RoleResourceDO;
import tech.oldhorse.shop.dao.sys.repository.ResourceRepository;
import tech.oldhorse.shop.dao.sys.repository.RoleResourceRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.sys.ResourceService;
import tech.oldhorse.shop.service.sys.condition.ResourceCondition;
import tech.oldhorse.shop.service.sys.convert.ResourceCoreConvert;
import tech.oldhorse.shop.service.sys.enums.ResourceTypeEnum;
import tech.oldhorse.shop.service.sys.object.model.ResourceModel;

import java.util.List;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {
    private final static String EXIST_SQL = "SELECT 1 FROM sys_role_resource WHERE sys_role_resource.resource_id = sys_resource.resource_id and role_id IN (%s)";
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
        AssertUtils.notNull(resourceModel.getResourceType(), "资源类型不能为空");
        if (resourceModel.getResourceType() == ResourceTypeEnum.API) {
            AssertUtils.notNull(resourceModel.getPerms(), "权限标识不能为空");
        } else if (resourceModel.getResourceType() == ResourceTypeEnum.MENU) {
            AssertUtils.notNull(resourceModel.getUrl(), "菜单URL不能为空");
        }

        if (resourceModel.getSort() == null) {
            resourceModel.setSort(getMaxSort() + 1);
        }
        if (resourceModel.getParentResourceId() != null) {
            ResourceModel parentResource = getByResourceId(resourceModel.getParentResourceId());
            AssertUtils.notNull(parentResource, "父资源不存在");
        }

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

        if (resourceModel.getSort() == null) {
            resourceModel.setSort(getMaxSort() + 1);
        }


        if (resourceModel.getParentResourceId() != null) {
            ResourceModel parentResource = getByResourceId(resourceModel.getParentResourceId());
            AssertUtils.notNull(parentResource, "父资源不存在");
        }

        ResourceDO resourceDO = resourceCoreConvert.model2Do(resourceModel);
        resourceDO.setId(resourceInDb.getId());
        return resourceRepository.updateById(resourceDO);
    }

    private Integer getMaxSort() {
        return resourceRepository.lambdaQuery()
                .eq(ResourceDO::getDeletedFlag, false)
                .orderByDesc(ResourceDO::getSort)
                .last("limit 1")
                .oneOpt()
                .map(ResourceDO::getSort)
                .orElse(0);
    }

    @Override
    public Boolean delete(String resourceId) {
        ResourceModel resourceInDb = getByResourceId(resourceId);
        AssertUtils.notNull(resourceInDb);

        ResourceDO childDO = resourceRepository.lambdaQuery().eq(ResourceDO::getParentResourceId, resourceId).eq(ResourceDO::getDeletedFlag, false).last("limit 1").one();
        AssertUtils.isNull(childDO, "资源还有子资源，无法删除");

        RoleResourceDO one = roleResourceRepository.lambdaQuery().eq(RoleResourceDO::getResourceId, resourceId).last("limit 1").one();
        AssertUtils.isNull(one, "资源被角色引用，无法删除");

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
