package ru.rosbank.javaschool.graduationprojectjava.exception;

public class ComicsNotFoundException extends RuntimeException{
    public ComicsNotFoundException() {
        super();
    }

    public ComicsNotFoundException(String message) {
        super(message);
    }

    public ComicsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComicsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ComicsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
