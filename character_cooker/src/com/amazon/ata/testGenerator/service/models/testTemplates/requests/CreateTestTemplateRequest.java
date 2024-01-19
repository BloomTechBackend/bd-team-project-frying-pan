package com.amazon.ata.testGenerator.service.models.testTemplates.requests;

import java.util.List;
import java.util.Objects;

public class CreateTestTemplateRequest {
    private String title;
    private String username;
    private List<String> termIdList;

    public CreateTestTemplateRequest() {}

    public CreateTestTemplateRequest(String title, String username, String dateModified, List<String> termIdList) {
        this.title = title;
        this.username = username;
        this.termIdList = termIdList;
    }

    public CreateTestTemplateRequest(Builder builder) {
        this.title = builder.title;
        this.username = builder.username;
        this.termIdList = builder.termIdList;
    }

    public String getTitle() {
        return title;
    }

    public CreateTestTemplateRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CreateTestTemplateRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<String> getTermIdList() {
        return termIdList;
    }

    public CreateTestTemplateRequest setTermIdList(List<String> termIdList) {
        this.termIdList = termIdList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTestTemplateRequest that = (CreateTestTemplateRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(username, that.username) && Objects.equals(termIdList, that.termIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, username, termIdList);
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String title;
        private String username;
        private List<String> termIdList;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withTermIdList(List<String> termIdList) {
            this.termIdList = termIdList;
            return this;
        }

        public CreateTestTemplateRequest build() {
            return new CreateTestTemplateRequest(this);
        }
    }
}
