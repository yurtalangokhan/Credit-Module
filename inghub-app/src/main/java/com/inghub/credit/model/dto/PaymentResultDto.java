package com.inghub.credit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentResultDto {

    private int installmentsPaidCount;
    private BigDecimal totalPaid;
    private Boolean isLoanFullyPaid;
    private BigDecimal unpaidAmount;
}
