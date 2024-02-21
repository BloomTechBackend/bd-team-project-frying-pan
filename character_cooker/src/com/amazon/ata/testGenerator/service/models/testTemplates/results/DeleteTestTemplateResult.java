package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.TermModel;

import java.util.List;
import java.util.Objects;

public class DeleteTestTemplateResult {
    private TemplateModel template;
    private List<TermModel> terms;

    public DeleteTestTemplateResult(Builder builder) {
        this.template = builder.template;
        this.terms = builder.terms;
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

    public List<TermModel> getTerms() {
        return terms;
    }

    public void setTerms(List<TermModel> terms) {
        this.terms = terms;
    }

    public static final class Builder {
        private TemplateModel template;;
        private List<TermModel> terms;

        public Builder withTemplate(TemplateModel template) {
            this.template = template;
            return this;
        }

        public Builder withTerms(List<TermModel> terms) {
            this.terms = terms;
            return this;
        }

        public DeleteTestTemplateResult build() {
            return new DeleteTestTemplateResult(this);
        }
    }
}
