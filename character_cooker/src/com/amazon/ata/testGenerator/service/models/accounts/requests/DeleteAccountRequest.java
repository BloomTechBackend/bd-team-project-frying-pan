package com.amazon.ata.testGenerator.service.models.accounts.requests;

import java.util.Objects;

public class DeleteAccountRequest {
    private String username;
    private String password;

    public DeleteAccountRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DeleteAccountRequest() {
    }

    public DeleteAccountRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public DeleteAccountRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DeleteAccountRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteAccountRequest that = (DeleteAccountRequest) o;
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

        public DeleteAccountRequest build() {
            return new DeleteAccountRequest(this);
        }
    }
}
