package tech.oldhorse.shop.service.prod.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.prod.entity.DictDO;
import tech.oldhorse.shop.service.prod.object.dto.DictDTO;
import tech.oldhorse.shop.service.prod.object.model.DictModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DictCoreConvert extends BaseConvert<DictDTO, DictModel, DictDO> {
}
