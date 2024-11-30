package com.inghub.core.common.dto;

import com.inghub.core.common.enumaration.EnumExceptionLevel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class ExceptionDetail implements Serializable {

    public ExceptionDetail() {
    }

    private String errorCode;

    private EnumExceptionLevel exceptionLevel;

    private String errorMessage;

    private transient Object[] errorArguments;

    private String service;

    private int httpStatus;
    private LocalDateTime dateTime;

    public ExceptionDetail(String errorCode) {
        this.errorCode = errorCode;
    }

    public ExceptionDetail(String errorCode, Object[] errorArguments) {
        this.errorCode = errorCode;
        this.errorArguments = errorArguments;
    }

    public ExceptionDetail(String errorCode, String errorMessage, Object[] errorArguments) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorArguments = errorArguments;
    }

    public ExceptionDetail(String errorCode, EnumExceptionLevel exceptionLevel) {
        this.errorCode = errorCode;
        this.exceptionLevel = exceptionLevel;
    }

    public ExceptionDetail(String errorCode, EnumExceptionLevel exceptionLevel, String errorMessage) {
        this.errorCode = errorCode;
        this.exceptionLevel = exceptionLevel;
        this.errorMessage = errorMessage;
    }

    public ExceptionDetail(String errorCode, EnumExceptionLevel exceptionLevel, String errorMessage, Object[] errorArguments, String service, LocalDateTime dateTime) {
        this.errorCode = errorCode;
        this.exceptionLevel = exceptionLevel;
        this.errorMessage = errorMessage;
        this.errorArguments = errorArguments;
        this.service = service;
        this.dateTime = dateTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public EnumExceptionLevel getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(EnumExceptionLevel exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object[] getErrorArguments() {
        return errorArguments;
    }

    public void setErrorArguments(Object[] errorArguments) {
        this.errorArguments = errorArguments;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}