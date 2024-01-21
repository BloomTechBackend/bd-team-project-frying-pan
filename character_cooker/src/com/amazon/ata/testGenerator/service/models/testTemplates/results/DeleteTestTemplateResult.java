package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.models.TemplateModel;

import java.util.Objects;

public class DeleteTestTemplateResult {
    private TemplateModel template;

    public DeleteTestTemplateResult(Builder builder) {
        this.template = builder.template;
    }

    public static Builder builder() {
        return new Builder();
    }

    public TemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(TemplateModel template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTestTemplateResult that = (DeleteTestTemplateResult) o;
        return Objects.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(template);
    }

    public static final class Builder {
        private TemplateModel template;;

        public Builder withTemplate(TemplateModel template) {
            this.template = template;
            return this;
        }

        public DeleteTestTemplateResult build() {
            return new DeleteTestTemplateResult(this);
        }
    }
}
