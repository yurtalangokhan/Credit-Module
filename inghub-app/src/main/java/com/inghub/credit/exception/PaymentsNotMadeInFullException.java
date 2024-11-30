package com.inghub.credit.exception;

import com.inghub.core.common.dto.ExceptionDetail;
import com.inghub.core.exceptionHandling.BaseUncheckedException;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class PaymentsNotMadeInFullException extends BaseUncheckedException {
    public static final String code = "com.inghub.credit.exception.payments_not_made_in_full_exception";

    public PaymentsNotMadeInFullException() {
        super(new ExceptionDetail(code));
    }

    public PaymentsNotMadeInFullException(String[] messageParams) {
        super(new ExceptionDetail(code, messageParams));
    }
}