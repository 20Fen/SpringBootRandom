package com.example.demo.exception;

/**
 * Description:
 */
public class CustomException extends RuntimeException {

    private int					errorCode;
    private String				message;

    public CustomException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
