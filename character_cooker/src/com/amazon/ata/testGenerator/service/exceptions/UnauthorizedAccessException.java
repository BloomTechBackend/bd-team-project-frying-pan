package com.amazon.ata.testGenerator.service.exceptions;

public class UnauthorizedAccessException extends RuntimeException{
    private static final long serialVersionUID = 704129481421721L;

    public UnauthorizedAccessException() {}

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedAccessException(Throwable cause) {
        super(cause);
    }

}
