package com.inghub.core.common.response;

import com.inghub.core.common.dto.ExceptionDetail;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class BaseResponse<T> implements Serializable {

    private T body;

    private ExceptionDetail exceptionDetail;

    private HttpStatus httpStatus;

    public BaseResponse() {
    }

    public BaseResponse(T body) {
        this.body = body;
    }

    public BaseResponse(T body, HttpStatus httpStatus) {
        this.body = body;
        this.httpStatus = httpStatus;
    }

    public BaseResponse(ExceptionDetail exceptionDetail, HttpStatus httpStatus) {
        this.exceptionDetail = exceptionDetail;
        this.httpStatus = httpStatus;
    }

    public BaseResponse(ExceptionDetail exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public BaseResponse(T body, ExceptionDetail exceptionDetail) {
        this.body = body;
        this.exceptionDetail = exceptionDetail;
    }

    public T getBody() {
        return body;
    }

    public ExceptionDetail getExceptionDetail() {
        return exceptionDetail;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}