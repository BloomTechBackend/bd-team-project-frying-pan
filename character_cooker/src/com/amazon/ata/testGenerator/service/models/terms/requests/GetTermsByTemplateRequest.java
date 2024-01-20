package com.amazon.ata.testGenerator.service.models.terms.requests;

import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.terms.results.GetTermsByTemplateResult;

import java.util.List;
import java.util.Objects;

public class GetTermsByTemplateRequest {
    private String templateId;

    public GetTermsByTemplateRequest() {
    }

    public GetTermsByTemplateRequest(Builder builder) {
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
        GetTermsByTemplateRequest that = (GetTermsByTemplateRequest) o;
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

        public GetTermsByTemplateRequest build() {
            return new GetTermsByTemplateRequest(this);
        }
    }
}
