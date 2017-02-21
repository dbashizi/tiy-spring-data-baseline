package com.tiy.adrian.model;

/**
 * Created by dbash on 2/10/2017.
 */
public class ErrorInformation {
    private String errorMessage;
    private Integer errorCode;

    public ErrorInformation(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ErrorInformation() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
