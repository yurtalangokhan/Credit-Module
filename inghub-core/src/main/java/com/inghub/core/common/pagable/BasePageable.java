package com.inghub.core.common.pagable;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BasePageable implements Serializable {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 25;

    private Integer pageNumber;

    private Integer pageSize;

    private BaseSort sort;


    public BasePageable() {
        this.pageNumber = DEFAULT_PAGE;
        this.pageSize = DEFAULT_SIZE;
        this.sort = new BaseSort();
    }

    public BasePageable(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = new BaseSort();
    }


    public BasePageable(Integer pageNumber, Integer pageSize, BaseSort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }


    public BasePageable(Integer pageNumber, Integer pageSize, Sort.Direction direction, String[] properties) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = new BaseSort(direction, properties);
    }


    public Integer getPageNumber() {
        return pageNumber;
    }


    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


    public Integer getPageSize() {
        return pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public BaseSort getSort() {
        return sort;
    }

    public void setSort(BaseSort sort) {
        this.sort = sort;
    }


}
