package com.amazon.ata.testGenerator.service.models.terms.requests;

import java.util.Objects;

public class CreateCustomTermRequest {
    private String romanization;
    private String symbol;
    private String templateId;
    private String username;

    private String definition;

    public CreateCustomTermRequest() {}

    public CreateCustomTermRequest(Builder builder) {
        this.romanization = builder.romanization;
        this.symbol = builder.symbol;
        this.templateId = builder.templateId;
        this.username = builder.username;
        this.definition = builder.definition;
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCustomTermRequest that = (CreateCustomTermRequest) o;
        return Objects.equals(romanization, that.romanization) && Objects.equals(symbol, that.symbol) && Objects.equals(templateId, that.templateId)
                && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(romanization, symbol, templateId, username);
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String romanization;
        private String symbol;
        private String templateId;
        private String username;
        private String definition;

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

        public Builder withDefinition(String definition) {
            this.definition = definition;
            return this;
        }

        public CreateCustomTermRequest build() {
            return new CreateCustomTermRequest(this);
        }
    }
}

