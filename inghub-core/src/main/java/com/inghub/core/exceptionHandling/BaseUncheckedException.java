package com.inghub.core.exceptionHandling;

import com.inghub.core.common.dto.ExceptionDetail;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseUncheckedException extends RuntimeException implements BaseException {

    private ExceptionDetail exceptionDetail;

    public BaseUncheckedException() {
    }

    public BaseUncheckedException(ExceptionDetail exceptionDetail) {
        super(exceptionDetail.getErrorMessage());
        this.exceptionDetail = exceptionDetail;
    }

    public BaseUncheckedException(String message, ExceptionDetail exceptionDetail) {
        super(message);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseUncheckedException(String message, Throwable cause, ExceptionDetail exceptionDetail) {
        super(message, cause);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseUncheckedException(Throwable cause, ExceptionDetail exceptionDetail) {
        super(exceptionDetail.getErrorMessage(), cause);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseUncheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionDetail exceptionDetail) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionDetail = exceptionDetail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExceptionDetail getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExceptionDetail(ExceptionDetail exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }


}