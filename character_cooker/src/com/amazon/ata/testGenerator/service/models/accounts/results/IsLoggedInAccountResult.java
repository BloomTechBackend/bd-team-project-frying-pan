package com.amazon.ata.testGenerator.service.models.accounts.results;

import java.util.Objects;

public class IsLoggedInAccountResult {
    private String logMessage;
    private String username;
    private String status;
    private boolean isLoggedIn;

    public IsLoggedInAccountResult() {}

    public IsLoggedInAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
        this.username = builder.username;
        this.status = builder.status;
        this.isLoggedIn = builder.isLoggedIn;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String logMessage;
        private String username;
        private String status;
        private boolean isLoggedIn;


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

        public Builder withLoggedIn(boolean loggedIn) {
            isLoggedIn = loggedIn;
            return this;
        }

        public IsLoggedInAccountResult build() {
            return new IsLoggedInAccountResult(this);
        }
    }
}
