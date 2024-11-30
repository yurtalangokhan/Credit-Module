package com.inghub.credit.model.constraint;

/**
 * @author gyurtalan
 * @version 1.0
 */
public final class CustomerConstraints {
    /**
     * Table Information
     */
    public static final String TABLE_NAME = "CUSTOMER";
    public static final String TABLE_SEQ_NAME = "CUSTOMER_SEQ";

    /**
     * Table Columns
     */
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_SURNAME = "SURNAME";
    public static final String COLUMN_CREDIT_LIMIT = "CREDIT_LIMIT";
    public static final String COLUMN_USED_CREDIT_LIMIT = "USED_CREDIT_LIMIT";


    /**
    * Constraints
    */
    public static final String UX_NAME_SURNAME = "UX_NAME_SURNAME";

    /**
     * Size Limits
     */
    public static final int MIN_NAME_SIZE = 2;
    public static final int MAX_NAME_SIZE = 30;
    public static final int MIN_SURNAME_SIZE = 2;
    public static final int MAX_SURNAME_SIZE = 30;

    public static final int CREDIT_LIMIT_INTEGER_SIZE = 13;
    public static final int CREDIT_LIMIT_FRACTION_SIZE = 3;
    public static final String CREDIT_LIMIT_VALUE_MIN = "0.00";

    public static final int USED_CREDIT_LIMIT_INTEGER_SIZE = 13;
    public static final int USED_CREDIT_LIMIT_FRACTION_SIZE = 3;

}
