package com.smt.example.exception;

public class ValidationException extends Exception {

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable exception) {
        super(message, exception);
    }

    public ValidationException(Throwable exception) {
        super(exception);
    }

}
