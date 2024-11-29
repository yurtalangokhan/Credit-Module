package com.inghub.core.common.pagable;

import org.springframework.data.domain.PageRequest;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BasePageableUtils {

    private BasePageableUtils() {
        throw new IllegalStateException("Cannot instantiate a utility class!");
    }

    public static PageRequest convert(BasePageable pageable) {
        return PageRequest.of(pageable.getPageNumber()
                , pageable.getPageSize()
                , BaseSortUtils.convert(pageable.getSort()));
    }
}
