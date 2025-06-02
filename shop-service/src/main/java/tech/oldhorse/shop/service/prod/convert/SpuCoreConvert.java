package tech.oldhorse.shop.service.prod.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.prod.entity.SpuDO;
import tech.oldhorse.shop.service.prod.object.dto.SpuDTO;
import tech.oldhorse.shop.service.prod.object.model.SpuModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpuCoreConvert extends BaseConvert<SpuDTO, SpuModel, SpuDO> {
}
