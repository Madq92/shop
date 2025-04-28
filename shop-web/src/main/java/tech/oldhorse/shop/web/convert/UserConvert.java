package tech.oldhorse.shop.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.service.object.dto.UserDTO;
import tech.oldhorse.shop.service.object.model.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConvert extends BaseConvert<UserDTO,UserModel> {

}
