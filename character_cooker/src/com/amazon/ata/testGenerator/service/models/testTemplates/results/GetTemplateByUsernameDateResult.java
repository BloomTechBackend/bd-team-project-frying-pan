package com.amazon.ata.testGenerator.service.models.testTemplates.results;

import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;

import java.util.List;
import java.util.Objects;

public class GetTemplateByUsernameDateResult {
    List<TemplateModel> templates;

    public GetTemplateByUsernameDateResult() {}

    public GetTemplateByUsernameDateResult(Builder builder) {
        this.templates = builder.templates;
    }

    public List<TemplateModel> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateModel> templates) {
        this.templates = templates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetTemplateByUsernameDateResult that = (GetTemplateByUsernameDateResult) o;
        return Objects.equals(templates, that.templates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templates);
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        List<TemplateModel> templates;

        public Builder withTemplates(List<TemplateModel> templates) {
            this.templates = templates;
            return this;
        }

        public GetTemplateByUsernameDateResult build() {
            return new GetTemplateByUsernameDateResult(this);
        }
    }



}

