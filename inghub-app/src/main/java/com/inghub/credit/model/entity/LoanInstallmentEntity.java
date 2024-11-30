package com.inghub.credit.model.entity;


import com.inghub.core.common.entity.BaseEntity;
import com.inghub.core.data.anotation.EntitySequence;
import com.inghub.credit.model.constraint.DatabaseConstants;
import com.inghub.credit.model.constraint.LoanInstallmentConstraints;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntitySequence(name = LoanInstallmentConstraints.TABLE_SEQ_NAME)
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = LoanInstallmentConstraints.TABLE_NAME)
public class LoanInstallmentEntity extends BaseEntity {

    @NotNull
    @Column(name = LoanInstallmentConstraints.COLUMN_LOAN_ID, nullable = false)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = LoanInstallmentConstraints.COLUMN_LOAN_ID, foreignKey = @ForeignKey(name = LoanInstallmentConstraints.FK_LOAN_ID), insertable = false, updatable = false)
    private CustomerEntity loanEntity;

    @NotNull
    @DecimalMin(value = LoanInstallmentConstraints.AMOUNT_VALUE_MIN)
    @Digits(integer = LoanInstallmentConstraints.AMOUNT_INTEGER_SIZE, fraction = LoanInstallmentConstraints.AMOUNT_FRACTION_SIZE)
    @Column(name = LoanInstallmentConstraints.COLUMN_AMOUNT, nullable = false)
    private BigDecimal amount;


    @Digits(integer = LoanInstallmentConstraints.PAID_AMOUNT_INTEGER_SIZE, fraction = LoanInstallmentConstraints.PAID_AMOUNT_FRACTION_SIZE)
    @Column(name = LoanInstallmentConstraints.COLUMN_PAID_AMOUNT)
    private BigDecimal paidAmount;

    @NotNull
    private LocalDateTime dueDate;

    private LocalDateTime paymentDate;

    @NotNull
    private Boolean hasBeenPaid;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public CustomerEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(CustomerEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Boolean getHasBeenPaid() {
        return hasBeenPaid;
    }

    public void setHasBeenPaid(Boolean hasBeenPaid) {
        this.hasBeenPaid = hasBeenPaid;
    }
}
