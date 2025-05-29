package tech.oldhorse.shop.service.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.sys.entity.ResourceDO;
import tech.oldhorse.shop.service.sys.object.dto.ResourceDTO;
import tech.oldhorse.shop.service.sys.object.model.ResourceModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceCoreConvert extends BaseConvert<ResourceDTO, ResourceModel, ResourceDO> {

}
