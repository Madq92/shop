package tech.oldhorse.shop.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.entity.ResourceDO;
import tech.oldhorse.shop.service.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.object.model.ResourceModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceCoreConvert extends BaseConvert<ResourceDTO, ResourceModel, ResourceDO> {

}
