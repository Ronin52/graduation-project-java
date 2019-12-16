package ru.rosbank.javaschool.graduationprojectjava.exception;

public class ContentTypeIsNullException extends RuntimeException {
    public ContentTypeIsNullException() {
        super();
    }

    public ContentTypeIsNullException(String message) {
        super(message);
    }

    public ContentTypeIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentTypeIsNullException(Throwable cause) {
        super(cause);
    }

    protected ContentTypeIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
