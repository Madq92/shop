package tech.oldhorse.shop.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.service.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.object.model.ResourceModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceConvert extends BaseConvert<ResourceDTO, ResourceModel> {

}
