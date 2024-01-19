package com.amazon.ata.testGenerator.service.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = -334712930215132L;

    public UsernameAlreadyExistsException() {
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    public UsernameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
