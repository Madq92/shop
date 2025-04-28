package tech.oldhorse.shop.common.utils;

import tech.oldhorse.shop.common.object.PageData;

import java.util.List;
import java.util.function.Function;

public class PageUtil {
    private PageUtil() {
    }


    public static <T> PageData<T> makeResponseData(List<T> dataList) {
        PageData<T> pageData = new PageData<>();
        pageData.setRecords(dataList);
        if (dataList instanceof PageData) {
            pageData.setTotal(((PageData<?>) dataList).getTotal());
        }
        return pageData;
    }

    public static <T> PageData<T> makeResponseData(List<T> dataList, Long totalCount) {
        PageData<T> pageData = new PageData<>();
        pageData.setRecords(dataList);
        if (totalCount != null) {
            pageData.setTotal(totalCount);
        }
        return pageData;
    }

    public static <R, T> PageData<R> makeResponse(PageData<T> page, Function<T, R> mapping) {
        PageData<R> pageResp = new PageData<>();
        pageResp.setTotal(page.getTotal());
        pageResp.setCurrent(page.getCurrent());
        pageResp.setSize(page.getSize());
        if (null != page.getRecords()) {
            pageResp.setRecords(page.getRecords().stream().map(mapping).toList());
        }
        return pageResp;
    }
}
