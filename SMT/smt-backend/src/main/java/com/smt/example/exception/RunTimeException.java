package com.smt.example.exception;

public class RunTimeException extends Exception {

    public RunTimeException() {
        super();
    }

    public RunTimeException(String message) {
        super(message);
    }

    public RunTimeException(String message, Throwable exception) {
        super(message, exception);
    }

    public RunTimeException(Throwable exception) {
        super(exception);
    }

}
