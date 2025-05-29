package tech.oldhorse.shop.service.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.sys.entity.OperLogDO;
import tech.oldhorse.shop.service.sys.object.dto.OperLogDTO;
import tech.oldhorse.shop.service.sys.object.model.OperLogModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OperLogCoreConvert extends BaseConvert<OperLogDTO, OperLogModel, OperLogDO> {
}
