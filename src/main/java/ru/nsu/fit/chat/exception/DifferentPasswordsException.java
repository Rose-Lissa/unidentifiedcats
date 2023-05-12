package ru.nsu.fit.chat.exception;

public class DifferentPasswordsException extends Exception{
    public DifferentPasswordsException() {
        super();
    }

    public DifferentPasswordsException(String message) {
        super(message);
    }

    public DifferentPasswordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DifferentPasswordsException(Throwable cause) {
        super(cause);
    }
}
