package tech.oldhorse.shop.service.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.sys.entity.RoleDO;
import tech.oldhorse.shop.service.sys.object.dto.RoleDTO;
import tech.oldhorse.shop.service.sys.object.model.RoleModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleCoreConvert extends BaseConvert<RoleDTO, RoleModel, RoleDO> {
}
