package com.amazon.ata.testGenerator.service.models.terms.results;

import com.amazon.ata.testGenerator.service.models.TermModel;

import java.util.List;

public class GetTermsByUsernameResult {
    private List<TermModel> terms;

    public GetTermsByUsernameResult() {
    }

    public GetTermsByUsernameResult(Builder builder) {
        this.terms = builder.terms;
    }

    public List<TermModel> getTerms() {
        return terms;
    }

    public void setTerms(List<TermModel> terms) {
        this.terms = terms;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<TermModel> terms;

        public Builder withTerms(List<TermModel> terms) {
            this.terms = terms;
            return this;
        }

        public GetTermsByUsernameResult build() {
            return new GetTermsByUsernameResult(this);
        }
    }
}
