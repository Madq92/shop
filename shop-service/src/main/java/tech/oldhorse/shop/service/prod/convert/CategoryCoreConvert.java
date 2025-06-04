package tech.oldhorse.shop.service.prod.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.prod.entity.CategoryDO;
import tech.oldhorse.shop.service.prod.object.dto.CategoryDTO;
import tech.oldhorse.shop.service.prod.object.model.CategoryModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryCoreConvert extends BaseConvert<CategoryDTO, CategoryModel, CategoryDO> {
}
