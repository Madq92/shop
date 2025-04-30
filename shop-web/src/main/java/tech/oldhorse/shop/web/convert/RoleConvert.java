package tech.oldhorse.shop.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.service.object.dto.RoleDTO;
import tech.oldhorse.shop.service.object.model.RoleModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface  RoleConvert extends BaseConvert<RoleDTO, RoleModel> {
}
