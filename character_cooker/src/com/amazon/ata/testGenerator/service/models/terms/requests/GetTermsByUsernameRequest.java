package com.amazon.ata.testGenerator.service.models.terms.requests;

import java.util.Objects;

public class GetTermsByUsernameRequest {
    private String username;

    public GetTermsByUsernameRequest() {
    }

    public GetTermsByUsernameRequest(Builder builder) {
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
        GetTermsByUsernameRequest that = (GetTermsByUsernameRequest) o;
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

        public GetTermsByUsernameRequest build() {
            return new GetTermsByUsernameRequest(this);
        }
    }
}
