package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.models.TemplateModel;

public class CreateTestTemplateResult {
    private TemplateModel template;

    public CreateTestTemplateResult(Builder builder) {
        this.template = builder.template;
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

        public CreateTestTemplateResult build() {
            return new CreateTestTemplateResult(this);
        }
    }


}
