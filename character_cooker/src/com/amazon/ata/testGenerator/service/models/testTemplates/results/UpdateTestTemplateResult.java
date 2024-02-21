package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.models.TemplateModel;

public class UpdateTestTemplateResult {
    private TemplateModel template;

    public UpdateTestTemplateResult(Builder builder) {
        this.template = builder.template;
    }

    public TemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(TemplateModel template) {
        this.template = template;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private TemplateModel template;;

        public Builder withTemplate(TemplateModel template) {
            this.template = template;
            return this;
        }

        public UpdateTestTemplateResult build() {
            return new UpdateTestTemplateResult(this);
        }
    }
}
