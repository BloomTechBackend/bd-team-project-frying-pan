package com.amazon.ata.testGenerator.service.models.testTemplates.requests;

import java.util.List;
import java.util.Objects;

public class UpdateTestTemplateRequest {
    private String templateId;
    private String title;
    private String username;
    private String dateModified;
    private List<String> hiraganaIdList;
    private List<String> katakanaIdList;

    public UpdateTestTemplateRequest() {}

    public UpdateTestTemplateRequest(Builder builder) {
        this.templateId = builder.templateId;
        this.title = builder.title;
        this.username = builder.username;
        this.dateModified = builder.dateModified;
        this.hiraganaIdList = builder.hiraganaIdList;
        this.katakanaIdList = builder.katakanaIdList;
    }

    public String getTemplateId() {
        return templateId;
    }

    public UpdateTestTemplateRequest setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UpdateTestTemplateRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UpdateTestTemplateRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDateModified() {
        return dateModified;
    }

    public UpdateTestTemplateRequest setDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public List<String> getHiraganaIdList() {
        return hiraganaIdList;
    }

    public UpdateTestTemplateRequest setHiraganaIdList(List<String> hiraganaIdList) {
        this.hiraganaIdList = hiraganaIdList;
        return this;
    }

    public List<String> getKatakanaIdList() {
        return katakanaIdList;
    }

    public UpdateTestTemplateRequest setKatakanaIdList(List<String> katakanaIdList) {
        this.katakanaIdList = katakanaIdList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTestTemplateRequest that = (UpdateTestTemplateRequest) o;
        return Objects.equals(templateId, that.templateId) && Objects.equals(title, that.title) && Objects.equals(username, that.username) && Objects.equals(dateModified, that.dateModified) && Objects.equals(hiraganaIdList, that.hiraganaIdList) && Objects.equals(katakanaIdList, that.katakanaIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, title, username, dateModified, hiraganaIdList, katakanaIdList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String templateId;
        private String title;
        private String username;
        private String dateModified;
        private List<String> hiraganaIdList;
        private List<String> katakanaIdList;


        public Builder withTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Builder withHiraganaIdList(List<String> hiraganaIdList) {
            this.hiraganaIdList = hiraganaIdList;
            return this;
        }

        public Builder withKatakanaIdList(List<String> katakanaIdList) {
            this.katakanaIdList = katakanaIdList;
            return this;
        }

        public UpdateTestTemplateRequest build() {
            return new UpdateTestTemplateRequest(this);
        }
    }
}
