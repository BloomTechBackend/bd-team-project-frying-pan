package com.amazon.ata.testGenerator.service.exceptions;

public class TermNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 9999666633331111L;

    public TermNotFoundException() {
    }

    public TermNotFoundException(String message) {
        super(message);
    }

    public TermNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TermNotFoundException(Throwable cause) {
        super(cause);
    }
}
