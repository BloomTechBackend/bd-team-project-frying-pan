package com.amazon.ata.testGenerator.service.models.accounts.results;

import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.TermModel;

import java.util.List;
import java.util.Objects;

public class DeleteAccountResult {
    private String logMessage;
    private String username;
    private List<TemplateModel> templatesDeleted;
    private List<TermModel> termsDeleted;

    public DeleteAccountResult() {}

    public DeleteAccountResult(Builder builder) {
        this.logMessage = builder.logMessage;
        this.username = builder.username;
        this.templatesDeleted = builder.templatesDeleted;
        this.termsDeleted = builder.termsDeleted;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public DeleteAccountResult setLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DeleteAccountResult setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<TemplateModel> getTemplatesDeleted() {
        return templatesDeleted;
    }

    public DeleteAccountResult setTemplatesDeleted(List<TemplateModel> templatesDeleted) {
        this.templatesDeleted = templatesDeleted;
        return this;
    }

    public List<TermModel> getTermsDeleted() {
        return termsDeleted;
    }

    public DeleteAccountResult setTermsDeleted(List<TermModel> termsDeleted) {
        this.termsDeleted = termsDeleted;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteAccountResult that = (DeleteAccountResult) o;
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
        private List<TemplateModel> templatesDeleted;
        private List<TermModel> termsDeleted;

        public Builder withLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withTemplatesDeleted(List<TemplateModel> templatesDeleted) {
            this.templatesDeleted = templatesDeleted;
            return this;
        }

        public Builder withTermsDeleted(List<TermModel> termsDeleted) {
            this.termsDeleted = termsDeleted;
            return this;
        }

        public DeleteAccountResult build() {
            return new DeleteAccountResult(this);
        }
    }
}
