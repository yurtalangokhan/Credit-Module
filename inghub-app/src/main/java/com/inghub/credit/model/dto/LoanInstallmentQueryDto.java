package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseDto;
import com.inghub.core.common.dto.BaseQueryDto;
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
public class LoanInstallmentQueryDto extends BaseQueryDto {

    private Long loanId;


    private LocalDateTime lessThanDueDate;
    private LocalDateTime greaterThanDueDate;

    private LocalDateTime lessThanPaymentDate;
    private LocalDateTime greaterThanPaymentDate;

    private Boolean isPaid;
}
