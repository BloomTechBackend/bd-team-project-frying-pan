package com.amazon.ata.testGenerator.service.models.terms.results;

import com.amazon.ata.testGenerator.service.models.TermModel;

public class DeleteCustomTermResult {
    private TermModel term;

    public DeleteCustomTermResult(Builder builder) {
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

        public DeleteCustomTermResult build() {
            return new DeleteCustomTermResult(this);
        }

    }
}
