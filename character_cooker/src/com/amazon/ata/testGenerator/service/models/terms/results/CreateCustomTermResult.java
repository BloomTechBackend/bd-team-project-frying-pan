package com.amazon.ata.testGenerator.service.models.terms.results;

import com.amazon.ata.testGenerator.service.models.TermModel;

public class CreateCustomTermResult {
    private TermModel term;

    public CreateCustomTermResult() {
    }

    public CreateCustomTermResult(Builder builder) {
        this.term = builder.term;
    }

    public TermModel getTerm() {
        return term;
    }

    public void setTerm(TermModel term) {
        this.term = term;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private TermModel term;

        public Builder withTerm(TermModel term) {
            this.term = term;
            return this;
        }

        public CreateCustomTermResult build() {
            return new CreateCustomTermResult(this);
        }
    }
}
