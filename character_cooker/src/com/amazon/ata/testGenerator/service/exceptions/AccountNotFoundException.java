package com.amazon.ata.testGenerator.service.exceptions;

public class AccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1111222233334444L;

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFoundException(Throwable cause) {
        super(cause);
    }
}
