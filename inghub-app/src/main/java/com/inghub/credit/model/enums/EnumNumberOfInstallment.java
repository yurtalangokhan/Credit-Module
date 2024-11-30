package com.inghub.credit.model.enums;

import com.inghub.core.common.enumaration.BaseEnum;
import com.inghub.core.common.enumaration.EnumConverter;

import java.util.Arrays;

/**
 * @author gyurtalan
 * @version 1.0
 */
public enum EnumNumberOfInstallment implements EnumConverter<Integer>, BaseEnum {
    Six("6", 6),
    Nine("9", 9),
    Twelve("12", 12),
    Twenty_Four("24", 24);

    private String label;
    private int code;

    EnumNumberOfInstallment(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public static EnumNumberOfInstallment enumFromValue(Integer code) {
        if (code == null)
            return null;
        return Arrays.stream(values()).filter(enumState -> code == enumState.code()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(code + " installment not found"));
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String label() {
        return this.label;
    }

    @Override
    public Integer toDbValue() {
        return this.code;
    }
}
