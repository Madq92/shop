package tech.oldhorse.shop.common.convert;

import java.util.List;

public interface BaseConvert<DTO, MODEL, DO> {
    MODEL dto2Model(DTO source);

    DTO model2Dto(MODEL source);

    List<MODEL> dtoList2ModelList(List<DTO> source);

    List<DTO> modelList2DtoList(List<MODEL> source);

    MODEL do2Model(DO source);

    DO model2Do(MODEL source);

    List<MODEL> doList2ModelList(List<DO> source);

    List<DO> modelList2DoList(List<MODEL> source);
}
