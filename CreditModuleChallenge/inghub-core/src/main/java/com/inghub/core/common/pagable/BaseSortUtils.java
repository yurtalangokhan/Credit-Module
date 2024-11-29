package com.inghub.core.common.pagable;

import org.springframework.data.domain.Sort;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseSortUtils {
    private BaseSortUtils() {
        throw new IllegalStateException("Cannot instantiate a utility class!");
    }

    public static Sort convert(BaseSort sort) {
        final Sort.Direction direction = sort.getDirection();
        final String[] properties = sort.getProperties();
        return switch (direction) {
            case ASC -> Sort.by(Sort.Direction.ASC, properties);
            case DESC -> Sort.by(Sort.Direction.DESC, properties);
        };
    }
}
