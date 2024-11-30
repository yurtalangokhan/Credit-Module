package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseDto;
import com.inghub.credit.model.constraint.LoanInstallmentConstraints;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanInstallmentDto extends BaseDto {

    @NotNull
    private Long loanId;

    @NotNull
    @DecimalMin(value = LoanInstallmentConstraints.AMOUNT_VALUE_MIN)
    @Digits(integer = LoanInstallmentConstraints.AMOUNT_INTEGER_SIZE, fraction = LoanInstallmentConstraints.AMOUNT_FRACTION_SIZE)
    private BigDecimal amount;


    @DecimalMin(value = LoanInstallmentConstraints.PAID_AMOUNT_VALUE_MIN)
    @Digits(integer = LoanInstallmentConstraints.PAID_AMOUNT_INTEGER_SIZE, fraction = LoanInstallmentConstraints.PAID_AMOUNT_FRACTION_SIZE)
    private BigDecimal paidAmount;

    @NotNull
    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    @NotNull
    private Boolean hasBeenPaid;
}
