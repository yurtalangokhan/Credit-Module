package com.inghub.credit.model.constraint;

public final class LoanConstraints {

    /**
     * Table Information
     */
    public static final String TABLE_NAME = "LOAN";
    public static final String TABLE_SEQ_NAME = "LOAN_SEQ";

    /**
     * Table Columns
     */
    public static final String COLUMN_CUSTOMER_ID = "CUSTOMER_ID";
    public static final String COLUMN_LOAN_AMOUNT = "LOAN_AMOUNT";
    public static final String COLUMN_NUMBER_OF_INSTALLMENT = "NUMBER_OF_INSTALLMENT";
    public static final String COLUMN_INTEREST_RATE="INTEREST_RATE";
    public static final String COLUMN_IS_PAID = "IS_PAID";


    /**
     * Size Limits
     */
    public static final int LOAN_AMOUNT_INTEGER_SIZE = 13;
    public static final int LOAN_AMOUNT_FRACTION_SIZE = 3;
    public static final String LOAN_AMOUNT_VALUE_MIN = "0.00";

    public static final int INTEREST_RATE_INTEGER_SIZE = 1;
    public static final int INTEREST_RATE_FRACTION_SIZE = 1;
    public static final String INTEREST_RATE_VALUE_MIN = "0.1";
    public static final String INTEREST_RATE_VALUE_MAX = "0.5";

    /**
     * Constraints
     */
    public static final String FK_CUSTOMER_ID = "FK_CUSTOMER_ID";
}
