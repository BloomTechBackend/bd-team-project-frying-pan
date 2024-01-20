package com.amazon.ata.testGenerator.service.models.testTemplates.requests;

import java.util.List;
import java.util.Objects;

public class CreateTestTemplateRequest {
    private String title;
    private String username;
    private List<String> hiraganaIdList;
    private List<String> katakanaIdList;

    public CreateTestTemplateRequest() {}

    public CreateTestTemplateRequest(String title, String username, String dateModified,
                                     List<String> hiraganaIdList, List<String> katakanaIdList) {
        this.title = title;
        this.username = username;
        this.hiraganaIdList = hiraganaIdList;
        this.katakanaIdList = katakanaIdList;
    }

    public CreateTestTemplateRequest(Builder builder) {
        this.title = builder.title;
        this.username = builder.username;
        this.hiraganaIdList = builder.hiraganaIdList;
        this.katakanaIdList = builder.katakanaIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getHiraganaIdList() {
        return hiraganaIdList;
    }

    public void setHiraganaIdList(List<String> hiraganaIdList) {
        this.hiraganaIdList = hiraganaIdList;
    }

    public List<String> getKatakanaIdList() {
        return katakanaIdList;
    }

    public void setKatakanaIdList(List<String> katakanaIdList) {
        this.katakanaIdList = katakanaIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTestTemplateRequest that = (CreateTestTemplateRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(username, that.username) && Objects.equals(hiraganaIdList, that.hiraganaIdList) && Objects.equals(katakanaIdList, that.katakanaIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, username, hiraganaIdList, katakanaIdList);
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String title;
        private String username;
        private List<String> hiraganaIdList;
        private List<String> katakanaIdList;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
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

        public CreateTestTemplateRequest build() {
            return new CreateTestTemplateRequest(this);
        }
    }
}
