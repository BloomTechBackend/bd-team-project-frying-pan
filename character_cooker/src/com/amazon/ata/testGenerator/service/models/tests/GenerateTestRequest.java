package com.amazon.ata.testGenerator.service.models.tests;

import java.util.List;
import java.util.Objects;

public class GenerateTestRequest {
    private List<String> hiraganaIds;
    private List<String> katakanaIds;
    private List<String> customIds;
    private boolean isHiraganaRandom;
    private boolean isKatakanaRandom;
    private boolean isCustomRandom;

    private String title;

    public GenerateTestRequest() {}

    public GenerateTestRequest(List<String> hiraganaIds, List<String> katakanaIds, List<String> customIds, boolean isHiraganaRandom, boolean isKatakanaRandom, boolean isCustomRandom, String title) {
        this.hiraganaIds = hiraganaIds;
        this.katakanaIds = katakanaIds;
        this.customIds = customIds;
        this.isHiraganaRandom = isHiraganaRandom;
        this.isKatakanaRandom = isKatakanaRandom;
        this.isCustomRandom = isCustomRandom;
        this.title = title;
    }

    public GenerateTestRequest(Builder builder) {
        this.hiraganaIds = builder.hiraganaIds;
        this.katakanaIds = builder.katakanaIds;
        this.customIds = builder.customIds;
        this.isHiraganaRandom = builder.isHiraganaRandom;
        this.isKatakanaRandom = builder.isKatakanaRandom;
        this.isCustomRandom = builder.isRandomCustom;
        this.title = builder.title;
    }

    public List<String> getHiraganaIds() {
        return hiraganaIds;
    }

    public void setHiraganaIds(List<String> hiraganaIds) {
        this.hiraganaIds = hiraganaIds;
    }

    public List<String> getKatakanaIds() {
        return katakanaIds;
    }

    public void setKatakanaIds(List<String> katakanaIds) {
        this.katakanaIds = katakanaIds;
    }

    public List<String> getCustomIds() {
        return customIds;
    }

    public void setCustomIds(List<String> customIds) {
        this.customIds = customIds;
    }

    public boolean isHiraganaRandom() {
        return isHiraganaRandom;
    }

    public GenerateTestRequest setHiraganaRandom(boolean hiraganaRandom) {
        isHiraganaRandom = hiraganaRandom;
        return this;
    }

    public boolean isKatakanaRandom() {
        return isKatakanaRandom;
    }

    public GenerateTestRequest setKatakanaRandom(boolean katakanaRandom) {
        isKatakanaRandom = katakanaRandom;
        return this;
    }

    public boolean isCustomRandom() {
        return isCustomRandom;
    }

    public GenerateTestRequest setCustomRandom(boolean customRandom) {
        isCustomRandom = customRandom;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GenerateTestRequest that = (GenerateTestRequest) o;
//        return isRandomHiragana == that.isRandomHiragana
//                && isRandomKatakana == that.isRandomKatakana
//                && isRandomCustom == that.isRandomCustom
//                && Objects.equals(hiraganaIds, that.hiraganaIds)
//                && Objects.equals(katakanaIds, that.katakanaIds)
//                && Objects.equals(customIds, that.customIds)
//                && Objects.equals(title, that.title);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerateTestRequest request = (GenerateTestRequest) o;
        return isHiraganaRandom == request.isHiraganaRandom && isKatakanaRandom == request.isKatakanaRandom && isCustomRandom == request.isCustomRandom && Objects.equals(hiraganaIds, request.hiraganaIds) && Objects.equals(katakanaIds, request.katakanaIds) && Objects.equals(customIds, request.customIds) && Objects.equals(title, request.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hiraganaIds, katakanaIds, customIds, isHiraganaRandom, isKatakanaRandom, isCustomRandom, title);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<String> hiraganaIds;
        private List<String> katakanaIds;
        private List<String> customIds;
        private boolean isHiraganaRandom;
        private boolean isKatakanaRandom;
        private boolean isRandomCustom;
        private String title;

        public Builder withHiraganaIds(List<String> hiraganaIds) {
            this.hiraganaIds = hiraganaIds;
            return this;
        }

        public Builder withKatakanaIds(List<String> katakanaIds) {
            this.katakanaIds = katakanaIds;
            return this;
        }

        public Builder withCustomIds(List<String> customIds) {
            this.customIds = customIds;
            return this;
        }

        public Builder withIsHiraganaRandom(boolean isHiraganaRandom) {
            this.isHiraganaRandom = isHiraganaRandom;
            return this;
        }

        public Builder withIsKatakanaRandom(boolean isKatakanaRandom) {
            this.isKatakanaRandom = isKatakanaRandom;
            return this;
        }

        public Builder withIsRandomCustom(boolean isRandomCustom) {
            this.isRandomCustom = isRandomCustom;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public GenerateTestRequest build() {
            return new GenerateTestRequest(this);
        }
    }
}
