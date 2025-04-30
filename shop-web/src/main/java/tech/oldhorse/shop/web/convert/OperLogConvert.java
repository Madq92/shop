package tech.oldhorse.shop.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.service.object.dto.OperLogDTO;
import tech.oldhorse.shop.service.object.model.OperLogModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OperLogConvert extends BaseConvert<OperLogDTO, OperLogModel> {
}
