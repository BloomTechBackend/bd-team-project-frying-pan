package com.amazon.ata.testGenerator.service.models.accounts.requests;

import java.util.Objects;

public class LoginAccountRequest {
    private String username;
    private String password;

    public LoginAccountRequest() {
    }

    public LoginAccountRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginAccountRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginAccountRequest that = (LoginAccountRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String password;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public LoginAccountRequest build() {
            return new LoginAccountRequest(this);
        }
    }
}
