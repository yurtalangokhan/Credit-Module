package com.inghub.core.common.dto;


import com.inghub.core.common.pagable.BasePageable;

import java.io.Serializable;
import java.util.Map;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseQueryDto implements Serializable {
    private BasePageable pageable;

    private Map<String, Object> filters;


    public BaseQueryDto() {
        this.pageable = new BasePageable();
    }

    public BaseQueryDto(BasePageable pageable) {
        this.pageable = pageable;
    }

    public BaseQueryDto(Map<String, Object> filters) {
        this.filters = filters;
    }

    public BaseQueryDto(BasePageable pageable, Map<String, Object> filters) {
        this.pageable = pageable;
        this.filters = filters;
    }

    public BasePageable getPageable() {
        return pageable;
    }

    public void setPageable(BasePageable pageable) {
        this.pageable = pageable;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }
}
