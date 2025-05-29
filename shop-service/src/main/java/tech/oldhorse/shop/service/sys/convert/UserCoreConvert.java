package tech.oldhorse.shop.service.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.sys.entity.UserDO;
import tech.oldhorse.shop.service.sys.object.dto.UserDTO;
import tech.oldhorse.shop.service.sys.object.model.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCoreConvert extends BaseConvert<UserDTO, UserModel, UserDO> {
}
