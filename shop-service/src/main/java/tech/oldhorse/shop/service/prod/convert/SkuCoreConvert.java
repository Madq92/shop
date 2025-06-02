package tech.oldhorse.shop.service.prod.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.oldhorse.shop.common.convert.BaseConvert;
import tech.oldhorse.shop.dao.prod.entity.SkuDO;
import tech.oldhorse.shop.service.prod.object.dto.SkuDTO;
import tech.oldhorse.shop.service.prod.object.model.SkuModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkuCoreConvert extends BaseConvert<SkuDTO, SkuModel, SkuDO> {
}
