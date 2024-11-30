package com.inghub.credit.model.converter;

import com.inghub.core.common.enumaration.converter.BaseEnumConverter;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;


public class EnumNumberOfInstallmentConverter extends BaseEnumConverter<Integer, EnumNumberOfInstallment> {
    @Override
    public EnumNumberOfInstallment convertToEntityAttribute(Integer dbData) {
        return EnumNumberOfInstallment.enumFromValue(dbData);
    }
}
