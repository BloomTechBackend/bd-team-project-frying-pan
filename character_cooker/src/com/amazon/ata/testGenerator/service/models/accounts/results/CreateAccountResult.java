package com.amazon.ata.testGenerator.service.models.accounts.results;

import java.util.Objects;

public class CreateAccountResult {
    private String logMessage;
    private String username;

    public CreateAccountResult() {}

    public CreateAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
        this.username = builder.username;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountResult that = (CreateAccountResult) o;
        return Objects.equals(logMessage, that.logMessage)
                 && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logMessage, username);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String logMessage;
        private String username;

        public Builder withLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public CreateAccountResult build() {
                return new CreateAccountResult(this);
        }
    }
}
