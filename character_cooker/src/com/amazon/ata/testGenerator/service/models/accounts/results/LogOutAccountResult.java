package com.amazon.ata.testGenerator.service.models.accounts.results;

import java.util.Objects;

public class LogOutAccountResult {
    private String logMessage;
    private String username;
    private String status;

    public LogOutAccountResult(String logMessage) {
        this.logMessage = logMessage;
    }

    public LogOutAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
        this.username = builder.username;
        this.status = builder.status;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public LogOutAccountResult setLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LogOutAccountResult setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public LogOutAccountResult setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogOutAccountResult that = (LogOutAccountResult) o;
        return Objects.equals(logMessage, that.logMessage) && Objects.equals(username, that.username) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logMessage, username, status);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String logMessage;
        private String username;
        private String status;


        public Builder withLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public LogOutAccountResult build() {
            return new LogOutAccountResult(this);
        }
    }
}
