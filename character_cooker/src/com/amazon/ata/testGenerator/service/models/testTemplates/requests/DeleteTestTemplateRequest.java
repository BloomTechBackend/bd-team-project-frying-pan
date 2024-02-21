package com.amazon.ata.testGenerator.service.models.testTemplates.requests;

import java.util.Objects;

public class DeleteTestTemplateRequest {
    private String templateId;

    public DeleteTestTemplateRequest() {
    }

    public DeleteTestTemplateRequest(Builder builder) {
        this.templateId = builder.templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTestTemplateRequest that = (DeleteTestTemplateRequest) o;
        return Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String templateId;

        public Builder withTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public DeleteTestTemplateRequest build() { return new DeleteTestTemplateRequest(this); }
    }
}
