package com.inghub.credit.model.entity;

import com.inghub.core.common.entity.BaseEntity;
import com.inghub.core.data.anotation.EntitySequence;
import com.inghub.credit.model.constraint.CustomerConstraints;
import com.inghub.credit.model.constraint.DatabaseConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Entity
@EntitySequence(name = CustomerConstraints.TABLE_SEQ_NAME)
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = CustomerConstraints.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {CustomerConstraints.COLUMN_NAME, CustomerConstraints.COLUMN_SURNAME},
                        name = CustomerConstraints.UX_NAME_SURNAME
                )
        })
public class CustomerEntity extends BaseEntity {

    @NotBlank
    @Size(min = CustomerConstraints.MIN_NAME_SIZE, max = CustomerConstraints.MAX_NAME_SIZE)
    @Column(name = CustomerConstraints.COLUMN_NAME, nullable = false)
    private String name;

    @NotBlank
    @Size(min = CustomerConstraints.MIN_SURNAME_SIZE, max = CustomerConstraints.MAX_SURNAME_SIZE)
    @Column(name = CustomerConstraints.COLUMN_SURNAME, nullable = false)
    private String surname;


    @NotNull
    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.CREDIT_LIMIT_FRACTION_SIZE)
    @Column(name = CustomerConstraints.COLUMN_CREDIT_LIMIT, nullable = false)
    private BigDecimal creditLimit;

    @NotNull
    @DecimalMin(value = CustomerConstraints.CREDIT_LIMIT_VALUE_MIN)
    @Digits(integer = CustomerConstraints.USED_CREDIT_LIMIT_INTEGER_SIZE, fraction = CustomerConstraints.USED_CREDIT_LIMIT_FRACTION_SIZE)
    @Column(name = CustomerConstraints.COLUMN_USED_CREDIT_LIMIT, nullable = false)
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
