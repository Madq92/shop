package tech.oldhorse.shop.service.prod.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.oldhorse.shop.common.utils.AssertUtils;
import tech.oldhorse.shop.dao.prod.entity.CategoryDO;
import tech.oldhorse.shop.dao.prod.repository.CategoryRepository;
import tech.oldhorse.shop.integration.sequence.wrapper.IdGeneratorWrapper;
import tech.oldhorse.shop.service.prod.CategoryService;
import tech.oldhorse.shop.service.prod.condition.CategoryCondition;
import tech.oldhorse.shop.service.prod.convert.CategoryCoreConvert;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryCoreConvert categoryCoreConvert;
    @Autowired
    IdGeneratorWrapper idGenerator;

    @Override
    public String create(CategoryModel categoryModel) {
        if (StringUtils.isNotEmpty(categoryModel.getParentId())) {
            CategoryModel parentCategoryModel = getByCategoryId(categoryModel.getParentId());
            AssertUtils.notNull(parentCategoryModel, "parent category not exist");
        }
        String categoryId = idGenerator.nextStringId();
        categoryModel.setCategoryId(categoryId);
        CategoryDO categoryDO = categoryCoreConvert.model2Do(categoryModel);
        categoryRepository.save(categoryDO);
        return categoryId;
    }

    @Override
    public Boolean edit(CategoryModel categoryModel) {
        if (StringUtils.isNotEmpty(categoryModel.getParentId())) {
            CategoryModel parentCategoryModel = getByCategoryId(categoryModel.getParentId());
            AssertUtils.notNull(parentCategoryModel, "parent category not exist");
        }
        CategoryModel byCategoryId = getByCategoryId(categoryModel.getCategoryId());
        AssertUtils.notNull(byCategoryId, "category not exist");

        CategoryDO categoryDO = categoryCoreConvert.model2Do(categoryModel);
        categoryDO.setId(byCategoryId.getId());
        return categoryRepository.updateById(categoryDO);
    }

    @Override
    public Boolean delete(String categoryId) {
        CategoryModel byCategoryId = getByCategoryId(categoryId);
        AssertUtils.notNull(byCategoryId, "category not exist");

        CategoryCondition condition = new CategoryCondition();
        condition.setParentId(categoryId);
        List<CategoryModel> categoryModels = listByCondition(condition);
        AssertUtils.isEmpty(categoryModels, "category has child");

        byCategoryId.setDeletedFlag(true);
        CategoryDO categoryDO = categoryCoreConvert.model2Do(byCategoryId);
        return categoryRepository.updateById(categoryDO);
    }

    @Override
    public CategoryModel getByCategoryId(String categoryId) {
        CategoryDO one = categoryRepository.lambdaQuery().eq(CategoryDO::getCategoryId, categoryId).eq(CategoryDO::getDeletedFlag, false).one();
        return categoryCoreConvert.do2Model(one);
    }

    @Override
    public List<CategoryModel> listByCondition(CategoryCondition condition) {
        List<CategoryDO> list = categoryRepository.lambdaQuery()
                .eq(StringUtils.isNotEmpty(condition.getParentId()), CategoryDO::getParentId, condition.getParentId())
                .like(StringUtils.isNotEmpty(condition.getNameLike()), CategoryDO::getName, condition.getNameLike())
                .eq(CategoryDO::getDeletedFlag, false)
                .list();
        return categoryCoreConvert.doList2ModelList(list);
    }
}
