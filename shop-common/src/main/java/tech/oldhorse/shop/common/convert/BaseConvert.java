package tech.oldhorse.shop.common.convert;

import java.util.List;

public interface BaseConvert<DTO, MODEL> {
    MODEL dto2Model(DTO source);

    DTO model2Dto(MODEL source);

    List<MODEL> dtoList2ModelList(List<DTO> source);

    List<DTO> modelList2DtoList(List<MODEL> source);
}
