package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseDto;
import com.inghub.credit.model.constraint.LoanConstraints;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanDto extends BaseDto {

    @NotNull
    private Long customerId;

    @NotNull
    @DecimalMin(value = LoanConstraints.LOAN_AMOUNT_VALUE_MIN)
    @Digits(integer = LoanConstraints.LOAN_AMOUNT_INTEGER_SIZE, fraction = LoanConstraints.LOAN_AMOUNT_FRACTION_SIZE)
    private BigDecimal loanAmount;

    @NotNull
    private EnumNumberOfInstallment numberOfInstallment;

    @NotNull
    @DecimalMin(value = LoanConstraints.INTEREST_RATE_VALUE_MIN)
    @DecimalMax(value = LoanConstraints.INTEREST_RATE_VALUE_MAX)
    @Digits(integer = LoanConstraints.INTEREST_RATE_INTEGER_SIZE, fraction = LoanConstraints.INTEREST_RATE_FRACTION_SIZE)
    private BigDecimal interestRate;

    private Boolean hasBeenPaid;
}
