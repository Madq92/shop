package tech.oldhorse.shop.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseCoreConvert;
import tech.oldhorse.shop.dao.entity.User;
import tech.oldhorse.shop.service.model.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConvert extends BaseCoreConvert<UserModel, User> {

}
