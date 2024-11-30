package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseQueryDto;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanQueryDto extends BaseQueryDto {

    private Long customerId;
    private BigDecimal lessThanLoanAmount;
    private BigDecimal greaterThanLoanAmount;
    private LocalDateTime lessThanCreateDate;
    private LocalDateTime greaterThanCreateDate;
    private EnumNumberOfInstallment numberOfInstallment;
    private Boolean hasBeenPaid;
}
