package tech.oldhorse.shop.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.service.object.dto.ConfigDTO;
import tech.oldhorse.shop.service.object.model.ConfigModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConfigConvert extends BaseConvert<ConfigDTO, ConfigModel> {
}
