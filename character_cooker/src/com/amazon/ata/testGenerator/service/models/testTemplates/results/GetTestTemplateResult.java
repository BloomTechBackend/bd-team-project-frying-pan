package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.models.TemplateModel;

import java.util.Objects;

public class GetTestTemplateResult {
    private TemplateModel template;

    public GetTestTemplateResult() {
    }

    public GetTestTemplateResult(Builder builder) {
        this.template = builder.template;
    }

    public TemplateModel getTemplate() {
        return template;
    }

    public GetTestTemplateResult setTemplate(TemplateModel template) {
        this.template = template;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTestTemplateResult that = (GetTestTemplateResult) o;
        return Objects.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(template);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private TemplateModel template;

        public Builder withTemplate(TemplateModel template) {
            this.template = template;
            return this;
        }

        public GetTestTemplateResult build() {
            return new GetTestTemplateResult(this);
        }
    }
}
