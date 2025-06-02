package tech.oldhorse.shop.service.prod.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.prod.entity.DictGroupDO;
import tech.oldhorse.shop.service.prod.object.dto.DictGroupDTO;
import tech.oldhorse.shop.service.prod.object.model.DictGroupModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DictGroupCoreConvert extends BaseConvert<DictGroupDTO, DictGroupModel, DictGroupDO> {
}
