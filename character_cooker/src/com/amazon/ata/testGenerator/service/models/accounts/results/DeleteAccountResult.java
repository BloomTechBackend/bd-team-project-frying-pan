package com.amazon.ata.testGenerator.service.models.accounts.results;

import java.util.Objects;

public class DeleteAccountResult {
    private String logMessage;

    public DeleteAccountResult(String logMessage) {
        this.logMessage = logMessage;
    }

    public DeleteAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public DeleteAccountResult setLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteAccountResult that = (DeleteAccountResult) o;
        return Objects.equals(logMessage, that.logMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logMessage);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String logMessage;

        public Builder withLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        public DeleteAccountResult build() {
            return new DeleteAccountResult(this);
        }
    }
}
