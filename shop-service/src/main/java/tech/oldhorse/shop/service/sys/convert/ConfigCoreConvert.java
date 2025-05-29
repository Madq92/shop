package tech.oldhorse.shop.service.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.sys.entity.ConfigDO;
import tech.oldhorse.shop.service.sys.object.dto.ConfigDTO;
import tech.oldhorse.shop.service.sys.object.model.ConfigModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConfigCoreConvert extends BaseConvert<ConfigDTO, ConfigModel, ConfigDO> {
}
