package com.inghub.credit.model.constraint;

public final class LoanInstallmentConstraints {

    /**
     * Table Information
     */
    public static final String TABLE_NAME = "LOAN_INSTALLMENT";
    public static final String TABLE_SEQ_NAME = "LOAN_INSTALLMENT_SEQ";
    /**
     * Table Columns
     */
    public static final String COLUMN_LOAN_ID = "LOAN_ID";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_PAID_AMOUNT = "PAID_AMOUNT";
    public static final String COLUMN_DUA_DATE = "DUA_DATE";
    public static final String COLUMN_PAYMENT_DATE = "PAYMENT_DATE";
    public static final String COLUMN_IS_PAID = "IS_PAID";
    /**
     * Size Limits
     */
    public static final int AMOUNT_INTEGER_SIZE = 13;
    public static final int AMOUNT_FRACTION_SIZE = 3;
    public static final String AMOUNT_VALUE_MIN = "0.00";

    public static final int PAID_AMOUNT_INTEGER_SIZE = 13;
    public static final int PAID_AMOUNT_FRACTION_SIZE = 3;
    public static final String PAID_AMOUNT_VALUE_MIN = "1.00";
    /**
     * Constraints
     */
    public static final String FK_LOAN_ID = "FK_LOAN_ID";
}
