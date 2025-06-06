package tech.oldhorse.shop.common.object;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;

@Getter
public class PageParam {

    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_MAX_SIZE = 2000;

    /**
     * 分页号码，从1开始计数。
     */
    private Integer pageNum;

    /**
     * 每页大小。
     */
    private Integer pageSize;

    /**
     * 是否统计totalCount
     */
    private Boolean count = true;

    public PageParam() {

    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 设置当前分页页号。
     *
     * @param pageNum 页号，如果传入非法值，则使用缺省值。
     */
    public void setPageNum(Integer pageNum) {
        if (pageNum == null) {
            return;
        }
        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        this.pageNum = pageNum;
    }

    /**
     * 设置分页的大小。
     *
     * @param pageSize 分页大小，如果传入非法值，则使用缺省值。
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            return;
        }
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageSize > DEFAULT_MAX_SIZE) {
            pageSize = DEFAULT_MAX_SIZE;
        }
        this.pageSize = pageSize;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public <T> Page<T> getPage() {
        return new Page<T>(pageNum, pageSize, count);
    }
}
