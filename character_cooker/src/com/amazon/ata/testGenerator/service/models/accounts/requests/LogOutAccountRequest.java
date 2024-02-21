package com.amazon.ata.testGenerator.service.models.accounts.requests;

import java.util.Objects;

public class LogOutAccountRequest {
    private String username;

    public LogOutAccountRequest() {
    }

    public LogOutAccountRequest(String username, String password) {
        this.username = username;
    }

    public LogOutAccountRequest(Builder builder) {
        this.username = builder.username;
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
        LogOutAccountRequest that = (LogOutAccountRequest) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public LogOutAccountRequest build() {
            return new LogOutAccountRequest(this);
        }
    }
}
