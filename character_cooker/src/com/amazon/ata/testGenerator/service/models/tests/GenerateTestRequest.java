package com.amazon.ata.testGenerator.service.models.tests;

import java.util.List;
import java.util.Objects;

public class GenerateTestRequest {
    private List<String> hiraganaIds;
    private List<String> katakanaIds;
    private List<String> customIds;
    private boolean isRandomHiragana;
    private boolean isRandomKatakana;
    private boolean isRandomCustom;

    private String title;

    public GenerateTestRequest() {}

    public GenerateTestRequest(Builder builder) {
        this.hiraganaIds = builder.hiraganaIds;
        this.katakanaIds = builder.katakanaIds;
        this.customIds = builder.customIds;
        this.isRandomHiragana = builder.isRandomHiragana;
        this.isRandomKatakana = builder.isRandomKatakana;
        this.isRandomCustom = builder.isRandomCustom;
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

    public boolean isRandomHiragana() {
        return isRandomHiragana;
    }

    public void setRandomHiragana(boolean randomHiragana) {
        isRandomHiragana = randomHiragana;
    }

    public boolean isRandomKatakana() {
        return isRandomKatakana;
    }

    public void setRandomKatakana(boolean randomKatakana) {
        isRandomKatakana = randomKatakana;
    }

    public boolean isRandomCustom() {
        return isRandomCustom;
    }

    public void setRandomCustom(boolean randomCustom) {
        isRandomCustom = randomCustom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerateTestRequest that = (GenerateTestRequest) o;
        return isRandomHiragana == that.isRandomHiragana
                && isRandomKatakana == that.isRandomKatakana
                && isRandomCustom == that.isRandomCustom
                && Objects.equals(hiraganaIds, that.hiraganaIds)
                && Objects.equals(katakanaIds, that.katakanaIds)
                && Objects.equals(customIds, that.customIds)
                && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hiraganaIds, katakanaIds, customIds,
                isRandomHiragana, isRandomKatakana, isRandomCustom,
                title);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<String> hiraganaIds;
        private List<String> katakanaIds;
        private List<String> customIds;
        private boolean isRandomHiragana;
        private boolean isRandomKatakana;
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

        public Builder withRandomHiragana(boolean isRandomHiragana) {
            this.isRandomHiragana = isRandomHiragana;
            return this;
        }

        public Builder withRandomKatakana(boolean isRandomKatakana) {
            this.isRandomKatakana = isRandomKatakana;
            return this;
        }

        public Builder withRandomCustom(boolean isRandomCustom) {
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
