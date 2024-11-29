package com.inghub.core.common.enumaration;

import java.util.Arrays;

/**
 * @author gyurtalan
 * @version 1.0
 */
public enum EnumExceptionLevel implements BaseEnum {
    HIGH_LEVEL("HIGH_LEVEL", 1),
    LOW_LEVEL("LOW_LEVEL", 2);

    private String label;
    private int code;

    EnumExceptionLevel(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public static EnumExceptionLevel fromValue(Integer code) {
        if (code == null)
            return null;
        return Arrays.stream(values()).filter(enumState -> code == enumState.code()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(code + " state not found"));
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }
}
