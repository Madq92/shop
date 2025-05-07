package tech.oldhorse.shop.service.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.entity.ConfigDO;
import tech.oldhorse.shop.service.object.dto.ConfigDTO;
import tech.oldhorse.shop.service.object.model.ConfigModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConfigCoreConvert extends BaseConvert<ConfigDTO, ConfigModel, ConfigDO> {
}
