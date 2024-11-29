package com.inghub.core.common.enumaration;

/**
 * @author gyurtalan
 * @version 1.0
 */
public interface EnumConverter<T> {
    T toDbValue();
}
