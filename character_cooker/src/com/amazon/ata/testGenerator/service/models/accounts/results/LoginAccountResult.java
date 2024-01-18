package com.amazon.ata.testGenerator.service.models.accounts.results;

import java.util.Objects;

public class LoginAccountResult {
    private String logMessage;
    private String username;

    public LoginAccountResult(String logMessage) {
        this.logMessage = logMessage;
    }

    public LoginAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
        this.username = builder.username;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public LoginAccountResult setLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginAccountResult setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginAccountResult that = (LoginAccountResult) o;
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
        private String username;

        public Builder withLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public LoginAccountResult build() {
            return new LoginAccountResult(this);
        }
    }
}
