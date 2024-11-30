package com.inghub.core.data.exception;

import com.inghub.core.common.dto.ExceptionDetail;
import com.inghub.core.exceptionHandling.BaseUncheckedException;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class EntityConstraintViolationException extends BaseUncheckedException {
    public static final String code = "com.inghub.core.data.exception.table_constraint_violation";

    public EntityConstraintViolationException() {
        super(new ExceptionDetail(code));
    }

    public EntityConstraintViolationException(String[] messageParams) {
        super(new ExceptionDetail(code, code, messageParams));
    }
}