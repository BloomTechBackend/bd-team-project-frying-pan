package com.amazon.ata.testGenerator.service.models.accounts.requests;

import java.util.Objects;

public class CreateAccountRequest {
    private String username;
    private String password;
    private String passwordConfirm;

    public CreateAccountRequest(String username, String password, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.passwordConfirm = builder.passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public CreateAccountRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateAccountRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public CreateAccountRequest setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(passwordConfirm, that.passwordConfirm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, passwordConfirm);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String password;
        private String passwordConfirm;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
            return this;
        }

        public CreateAccountRequest build() {
            return new CreateAccountRequest(this);
        }
    }
}
