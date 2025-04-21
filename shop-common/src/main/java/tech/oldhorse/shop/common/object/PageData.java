package tech.oldhorse.shop.common.object;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {
    /**
     * 数据列表。
     */
    private List<T> records;
    /**
     * 数据总数量。
     */
    private Long total;
    /**
     * 页大小
     */
    private Long size;
    /**
     * 当前页
     */
    private Long current;

    /**
     * 为了保持前端的数据格式兼容性，在没有数据的时候，需要返回空分页对象。
     *
     * @return 空分页对象。
     */
    public static <T> PageData<T> emptyPageData() {
        return new PageData<>(Lists.newArrayList(), 0L, 0L, 0L);
    }

    public static <M, D> PageData<M> convertFrom(Page<D> page, Function<D, M> mapper) {
        if (page == null || page.getRecords() == null){
            return emptyPageData();
        }
        List<M> dataList = page.getRecords().stream().map(mapper).collect(Collectors.toList());
        return new PageData<M>(dataList, page.getTotal(), page.getSize(), page.getCurrent());
    }
}
