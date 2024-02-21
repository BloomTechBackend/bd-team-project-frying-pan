package com.amazon.ata.testGenerator.service.models;

import java.util.Objects;

public class TermModel {

    private String termId;
    private String romanization;
    private String symbol;

    public TermModel() {

    }

    public TermModel(Builder builder) {
        this.termId = builder.termId;
        this.romanization = builder.romanization;
        this.symbol = builder.symbol;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getRomanization() {
        return romanization;
    }

    public void setRomanization(String romanization) {
        this.romanization = romanization;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermModel termModel = (TermModel) o;
        return Objects.equals(termId, termModel.termId)
                && Objects.equals(romanization, termModel.romanization)
                && Objects.equals(symbol, termModel.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId, romanization, symbol);
    }

    @Override
    public String toString() {
        return "TermModel{" +
                "termId='" + termId + '\'' +
                ", romanization='" + romanization + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String termId;
        private String romanization;
        private String symbol;

        public Builder withTermId(String termId) {
            this.termId = termId;
            return this;
        }

        public Builder withRomanization(String romanization) {
            this.romanization = romanization;
            return this;
        }

        public Builder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public TermModel build() {
            return new TermModel(this);
        }
    }

}
