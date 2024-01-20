package com.amazon.ata.testGenerator.service.models.terms.requests;

import java.util.Objects;

public class CreateCustomTermRequest {
    private String romanization;
    private String symbol;

    private String templateId;
    private String username;
    private String date;

    public CreateCustomTermRequest() {}

    public CreateCustomTermRequest(Builder builder) {
        this.romanization = builder.romanization;
        this.symbol = builder.symbol;
        this.templateId = builder.templateId;
        this.username = builder.username;
        this.date = builder.date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRomanization() {
        return romanization;
    }

    public void setRomanization(String romanization) {
        this.romanization = romanization;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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
        CreateCustomTermRequest that = (CreateCustomTermRequest) o;
        return Objects.equals(romanization, that.romanization) && Objects.equals(symbol, that.symbol) && Objects.equals(templateId, that.templateId) && Objects.equals(username, that.username) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(romanization, symbol, templateId, username, date);
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String romanization;
        private String symbol;

        private String templateId;
        private String username;
        private String date;

        public Builder withRomanization(String romanization) {
            this.romanization = romanization;
            return this;
        }

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public CreateCustomTermRequest build() {
            return new CreateCustomTermRequest(this);
        }
    }
}

