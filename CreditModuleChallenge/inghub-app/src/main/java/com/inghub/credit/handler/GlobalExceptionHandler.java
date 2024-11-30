package com.inghub.credit.handler;

import com.inghub.core.common.dto.ExceptionDetail;
import com.inghub.core.common.response.BaseResponse;
import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author gyurtalan
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    public GlobalExceptionHandler() {

    }

    @ExceptionHandler(EntityCouldNotBeFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse handleException(EntityCouldNotBeFoundException ex, Locale locale) {
        ex.setExceptionDetail(setExceptionDetail(ex.getExceptionDetail(), ex.getMessage(), HttpStatus.BAD_REQUEST));
        return new BaseResponse<>(ex.getExceptionDetail(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse handleException(IllegalArgumentException ex, Locale locale) {
        return new BaseResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse handleException(ObjectNotFoundException ex, Locale locale) {
        return new BaseResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



    private ExceptionDetail setExceptionDetail(ExceptionDetail exceptionDetail, String message, HttpStatus httpStatus) {
        exceptionDetail.setDateTime(LocalDateTime.now());
        if (exceptionDetail.getErrorArguments() != null) {
            exceptionDetail.setErrorMessage(String.format(message, exceptionDetail.getErrorArguments()));
        } else {
            exceptionDetail.setErrorMessage(message);
        }
        exceptionDetail.setHttpStatus(httpStatus.value());
        return exceptionDetail;
    }


}
