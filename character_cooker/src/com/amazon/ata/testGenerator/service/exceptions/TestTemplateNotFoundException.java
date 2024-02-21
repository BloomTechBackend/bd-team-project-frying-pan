package com.amazon.ata.testGenerator.service.exceptions;

public class TestTemplateNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 55566677778888L;

    public TestTemplateNotFoundException() {
    }

    public TestTemplateNotFoundException(String message) {
        super(message);
    }

    public TestTemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestTemplateNotFoundException(Throwable cause) {
        super(cause);
    }

}
