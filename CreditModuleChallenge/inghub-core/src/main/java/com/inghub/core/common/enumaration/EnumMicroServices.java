package com.inghub.core.common.enumaration;

import java.util.Arrays;

/**
 * @author gyurtalan
 * @version 1.0
 */

public enum EnumMicroServices implements BaseEnum {
    MANAGER_APP("MANAGER_APP", 1),
    MASTER_APP("MASTER_APP", 2),
    AUTH_APP("AUTH_APP", 3);

    private String label;
    private int code;

    EnumMicroServices(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public static EnumMicroServices fromValue(Integer code) {
        if (code == null)
            return null;
        return Arrays.stream(values()).filter(enumState -> code == enumState.code()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(code + " microservice not found"));
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
