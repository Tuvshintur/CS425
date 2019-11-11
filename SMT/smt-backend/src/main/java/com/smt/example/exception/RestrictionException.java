package com.smt.example.exception;

public class RestrictionException extends Exception {

    public RestrictionException() {
        super();
    }

    public RestrictionException(String message) {
        super(message);
    }

    public RestrictionException(String message, Throwable exception) {
        super(message, exception);
    }

    public RestrictionException(Throwable exception) {
        super(exception);
    }

}
