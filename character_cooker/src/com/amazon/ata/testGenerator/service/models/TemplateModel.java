package com.amazon.ata.testGenerator.service.models;

import java.util.List;
import java.util.Objects;

public class TemplateModel {
    private String templateId;
    private String title;
    private String username;
    private String dateModified;
    private List<String> termIdList;

    public TemplateModel() {
    }

    public TemplateModel(Builder builder) {
        this.templateId = builder.templateId;
        this.title = builder.title;
        this.username = builder.username;
        this.dateModified = builder.dateModified;
        this.termIdList = builder.termIdList;
    }

    public String getTemplateId() {
        return templateId;
    }

    public TemplateModel setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TemplateModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TemplateModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDateModified() {
        return dateModified;
    }

    public TemplateModel setDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public List<String> getTermIdList() {
        return termIdList;
    }

    public TemplateModel setTermIdList(List<String> termIdList) {
        this.termIdList = termIdList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateModel that = (TemplateModel) o;
        return Objects.equals(templateId, that.templateId) && Objects.equals(title, that.title) && Objects.equals(username, that.username) && Objects.equals(dateModified, that.dateModified) && Objects.equals(termIdList, that.termIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, title, username, dateModified, termIdList);
    }

    @Override
    public String toString() {
        return "TemplateModel{" +
                "templateId='" + templateId + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", termIdList=" + termIdList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String templateId;
        private String title;
        private String username;
        private String dateModified;
        private List<String> termIdList;

        public Builder withTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder setTitle(String title) {
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

        public Builder withTermIdList(List<String> termIdList) {
            this.termIdList = termIdList;
            return this;
        }

        public TemplateModel build() {
            return new TemplateModel(this);
        }
    }
}
