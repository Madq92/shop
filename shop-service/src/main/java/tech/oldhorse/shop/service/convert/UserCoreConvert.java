package tech.oldhorse.shop.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseCoreConvert;
import tech.oldhorse.shop.dao.entity.UserDO;
import tech.oldhorse.shop.service.object.model.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCoreConvert extends BaseCoreConvert<UserModel, UserDO> {

}
