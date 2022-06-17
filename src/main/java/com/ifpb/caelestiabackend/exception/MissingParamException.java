package com.ifpb.caelestiabackend.exception;

public class MissingParamException extends Exception {

    public MissingParamException() {
        super();
    }

    public MissingParamException(String message) {
        super(message);
    }

    public MissingParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingParamException(Throwable cause) {
        super(cause);
    }

    protected MissingParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
