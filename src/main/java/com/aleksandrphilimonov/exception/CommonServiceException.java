package com.aleksandrphilimonov.exception;

public class CommonServiceException extends Exception{
    public CommonServiceException() {
    }

    public CommonServiceException(Throwable cause) {
        super(cause);
    }

    public CommonServiceException(String message) {
        super(message);
    }
}
