package tech.oldhorse.shop.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseCoreConvert;
import tech.oldhorse.shop.dao.entity.RoleDO;
import tech.oldhorse.shop.service.object.model.RoleModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleCoreConvert extends BaseCoreConvert<RoleModel, RoleDO> {
}
