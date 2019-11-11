package com.smt.example.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable exception) {
        super(message, exception);
    }

    public NotFoundException(Throwable exception) {
        super(exception);
    }

}