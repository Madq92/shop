package tech.oldhorse.shop.common.convert;

import java.util.List;

public interface BaseCoreConvert<MODEL, DO> {
    MODEL do2Model(DO source);

    DO model2Do(MODEL source);

    List<MODEL> doList2ModelList(List<DO> source);

    List<DO> modelList2DoList(List<MODEL> source);
}
