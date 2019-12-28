package com.wdk.general.core.common.model;

/**
 * @author wdke
 * @date 2018/10/26
 */
public class PageParam {
    Integer pageNum=1;

    Integer pageSize=10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
