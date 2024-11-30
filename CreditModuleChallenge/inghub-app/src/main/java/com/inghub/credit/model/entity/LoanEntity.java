package com.inghub.credit.model.entity;


import com.inghub.core.common.entity.BaseEntity;
import com.inghub.core.data.anotation.EntitySequence;
import com.inghub.credit.model.constraint.DatabaseConstants;
import com.inghub.credit.model.constraint.LoanConstraints;
import com.inghub.credit.model.converter.EnumNumberOfInstallmentConverter;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@EntitySequence(name = LoanConstraints.TABLE_SEQ_NAME)
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = LoanConstraints.TABLE_NAME)
public class LoanEntity extends BaseEntity {

    @NotNull
    @Column(name = LoanConstraints.COLUMN_CUSTOMER_ID, nullable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = LoanConstraints.COLUMN_CUSTOMER_ID, foreignKey = @ForeignKey(name = LoanConstraints.FK_CUSTOMER_ID), insertable = false, updatable = false)
    private CustomerEntity customerEntity;


    @NotNull
    @DecimalMin(value = LoanConstraints.LOAN_AMOUNT_VALUE_MIN)
    @Digits(integer = LoanConstraints.LOAN_AMOUNT_INTEGER_SIZE, fraction = LoanConstraints.LOAN_AMOUNT_FRACTION_SIZE)
    @Column(name = LoanConstraints.COLUMN_LOAN_AMOUNT, nullable = false)
    private BigDecimal loanAmount;

    @NotNull
    @DecimalMin(value = LoanConstraints.INTEREST_RATE_VALUE_MIN)
    @DecimalMax(value = LoanConstraints.INTEREST_RATE_VALUE_MAX)
    @Digits(integer = LoanConstraints.INTEREST_RATE_INTEGER_SIZE, fraction = LoanConstraints.INTEREST_RATE_FRACTION_SIZE)
    @Column(name = LoanConstraints.COLUMN_INTEREST_RATE, nullable = false)
    private BigDecimal interestRate;

    @NotNull
    @Convert(converter = EnumNumberOfInstallmentConverter.class)
    @Column(name = LoanConstraints.COLUMN_NUMBER_OF_INSTALLMENT, nullable = false)
    private EnumNumberOfInstallment numberOfInstallment;

    @Column(name = LoanConstraints.COLUMN_IS_PAID, nullable = false)
    private Boolean hasBeenPaid;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public EnumNumberOfInstallment getNumberOfInstallment() {
        return numberOfInstallment;
    }

    public void setNumberOfInstallment(EnumNumberOfInstallment numberOfInstallment) {
        this.numberOfInstallment = numberOfInstallment;
    }

    public Boolean getHasBeenPaid() {
        return hasBeenPaid;
    }

    public void setHasBeenPaid(Boolean hasBeenPaid) {
        this.hasBeenPaid = hasBeenPaid;
    }
}
