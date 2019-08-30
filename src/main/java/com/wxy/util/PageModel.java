package com.wxy.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-8-30 下午3:20
 * @Description TODO
 **/
@Data
public class PageModel<T> {
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 页面尺寸
     */
    private int pageSize;
    /**
     * 总数据条数
     */
    private long total;
    /**
     * 数据列表
     */
    private List<T> list;

    public PageModel(PageInfo<T> pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }
}
