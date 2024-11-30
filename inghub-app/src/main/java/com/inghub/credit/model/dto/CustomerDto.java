package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseDto;
import com.inghub.credit.model.constraint.CustomerConstraints;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author gyurtalan
 * @version 1.0
 */

public class CustomerDto extends BaseDto {

    private String name;

    @NotBlank
    private String surname;

    @NotNull
    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal creditLimit;

    @NotNull
    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.USED_CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.USED_CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal usedCreditLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getUsedCreditLimit() {
        return usedCreditLimit;
    }

    public void setUsedCreditLimit(BigDecimal usedCreditLimit) {
        this.usedCreditLimit = usedCreditLimit;
    }
}
