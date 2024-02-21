package com.amazon.ata.testGenerator.service.models.terms.requests;

public class DeleteCustomTermRequest {
    private String termId;

    public DeleteCustomTermRequest() {
    }

    public DeleteCustomTermRequest(Builder builder) {
        this.termId = builder.termId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.amazon.ata.testGenerator.service.models.terms.requests.DeleteCustomTermRequest that = (com.amazon.ata.testGenerator.service.models.terms.requests.DeleteCustomTermRequest) o;
        return java.util.Objects.equals(termId, that.termId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(termId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String termId;

        public Builder withTermId(String termId) {
            this.termId = termId;
            return this;
        }

        public DeleteCustomTermRequest build() {
            return new DeleteCustomTermRequest(this);
        }
    }

}
