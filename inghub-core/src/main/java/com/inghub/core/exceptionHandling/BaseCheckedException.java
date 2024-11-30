package com.inghub.core.exceptionHandling;

import com.inghub.core.common.dto.ExceptionDetail;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseCheckedException extends Exception implements BaseException {

    private ExceptionDetail exceptionDetail;

    public BaseCheckedException() {
    }

    public BaseCheckedException(ExceptionDetail exceptionDetail) {
        super(exceptionDetail.getErrorMessage());
        this.exceptionDetail = exceptionDetail;
    }

    public BaseCheckedException(String message, ExceptionDetail exceptionDetail) {
        super(message);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseCheckedException(String message, Throwable cause, ExceptionDetail exceptionDetail) {
        super(message, cause);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseCheckedException(Throwable cause, ExceptionDetail exceptionDetail) {
        super(exceptionDetail.getErrorMessage(), cause);
        this.exceptionDetail = exceptionDetail;
    }

    public BaseCheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionDetail exceptionDetail) {
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
    public void setExceptionDetail(ExceptionDetail exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
