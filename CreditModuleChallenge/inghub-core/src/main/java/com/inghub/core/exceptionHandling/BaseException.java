package com.inghub.core.exceptionHandling;

import com.inghub.core.common.dto.ExceptionDetail;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */

public interface BaseException extends Serializable {

    ExceptionDetail getExceptionDetail();

    void setExceptionDetail(ExceptionDetail exceptionDetail);
}