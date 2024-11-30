package com.inghub.core.data.exception;

import com.inghub.core.common.dto.ExceptionDetail;
import com.inghub.core.exceptionHandling.BaseUncheckedException;

public class EntityCouldNotBeFoundException extends BaseUncheckedException {
    public static final String code = "%s, %s ENTITY_COULD_NOT_BE_FOUND";

    public EntityCouldNotBeFoundException() {
        super(new ExceptionDetail(code));
    }

    public EntityCouldNotBeFoundException(String entityName, String entityId) {
        super(new ExceptionDetail(code, String.format(code,entityId, entityName), new String[]{entityName, entityId}));
    }
}