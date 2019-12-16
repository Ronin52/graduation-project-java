package ru.rosbank.javaschool.graduationprojectjava.exception;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException() {
        super();
    }

    public CharacterNotFoundException(String message) {
        super(message);
    }

    public CharacterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CharacterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
