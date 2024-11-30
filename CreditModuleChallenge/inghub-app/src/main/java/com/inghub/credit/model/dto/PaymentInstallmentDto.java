package com.inghub.credit.model.dto;

import com.inghub.credit.model.constraint.LoanInstallmentConstraints;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentInstallmentDto {

    @NotNull
    private Long loanId;

    @NotNull
    @DecimalMin(value = LoanInstallmentConstraints.PAID_AMOUNT_VALUE_MIN)
    @Digits(integer = LoanInstallmentConstraints.PAID_AMOUNT_INTEGER_SIZE, fraction = LoanInstallmentConstraints.PAID_AMOUNT_FRACTION_SIZE)
    private BigDecimal amount;

}
