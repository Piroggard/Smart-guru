package org.example.exception;

public class IncorrectUserActionException extends RuntimeException {

    public IncorrectUserActionException(String message) {
        super(message);
    }
}
