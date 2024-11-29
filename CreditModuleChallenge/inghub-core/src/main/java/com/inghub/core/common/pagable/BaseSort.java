package com.inghub.core.common.pagable;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseSort implements Serializable {

    private static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;

    private static final String ID_FIELD = "id";

    private static final String[] DEFAULT_PROPERTIES = new String[]{ID_FIELD};

    private Sort.Direction direction;

    private String[] properties;

    /**
     * Instantiates a new HvlSort.
     */
    public BaseSort() {
        this.direction = DEFAULT_DIRECTION;
        this.properties = DEFAULT_PROPERTIES;
    }

    public BaseSort(Sort.Direction direction, String[] properties) {
        this.direction = direction;
        this.properties = properties;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }


    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
