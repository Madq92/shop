package tech.oldhorse.shop.service.prod;

import tech.oldhorse.shop.service.prod.condition.CategoryCondition;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

import java.util.List;

public interface CategoryService {
    String create(CategoryModel categoryModel);

    Boolean edit(CategoryModel categoryModel);

    Boolean delete(String categoryId);

    CategoryModel getByCategoryId(String categoryId);

    List<SpuModel> listByCondition(CategoryCondition condition);
}
