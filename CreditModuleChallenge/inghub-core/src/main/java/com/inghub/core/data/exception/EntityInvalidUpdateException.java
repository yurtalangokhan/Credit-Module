package com.inghub.core.data.exception;

import com.inghub.core.common.dto.ExceptionDetail;
import com.inghub.core.exceptionHandling.BaseUncheckedException;

public class EntityInvalidUpdateException extends BaseUncheckedException {
    public static final String code = "%s, com.inghub.core.data.exception.invalid_update";

    public EntityInvalidUpdateException() {
        super(new ExceptionDetail(code));
    }

    public EntityInvalidUpdateException(String entityId) {
        super(new ExceptionDetail(code, String.format(code, entityId), new String[]{entityId}));
    }
}