package com.smt.example.exception;

public class RMIException extends Exception {

    public RMIException() {
        super();
    }

    public RMIException(String message) {
        super(message);
    }

    public RMIException(String message, Throwable exception) {
        super(message, exception);
    }

    public RMIException(Throwable exception) {
        super(exception);
    }

}
