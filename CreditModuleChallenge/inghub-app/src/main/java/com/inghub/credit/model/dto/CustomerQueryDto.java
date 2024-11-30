package com.inghub.credit.model.dto;

import com.inghub.core.common.dto.BaseQueryDto;
import com.inghub.credit.model.constraint.CustomerConstraints;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author gyurtalan
 * @version 1.0
 */

@Getter
@Setter
public class CustomerQueryDto extends BaseQueryDto {
    private String name;
    private String surname;
    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal lessThanCreditLimit;

    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal greaterThanCreditLimit;

    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.USED_CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.USED_CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal lessThanUsedCreditLimit;

    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.USED_CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.USED_CREDIT_LIMIT_FRACTION_SIZE)
    private BigDecimal greaterThanUsedCreditLimit;
}
