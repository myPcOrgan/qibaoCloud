package com.qibao.common.dto;

import java.util.List;

public class BaseResponse<T> extends AbstractServiceResponse {

    /**
     * 返回的结果对象
     */
    private T result;
    /**
     * 返回的列表数据
     */
    private List<T> data;
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private long totalCount;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
