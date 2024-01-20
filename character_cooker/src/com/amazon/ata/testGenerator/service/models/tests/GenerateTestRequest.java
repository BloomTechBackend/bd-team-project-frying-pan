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

    public GenerateTestRequest setHiraganaIds(List<String> hiraganaIds) {
        this.hiraganaIds = hiraganaIds;
        return this;
    }

    public List<String> getKatakanaIds() {
        return katakanaIds;
    }

    public GenerateTestRequest setKatakanaIds(List<String> katakanaIds) {
        this.katakanaIds = katakanaIds;
        return this;
    }

    public List<String> getCustomIds() {
        return customIds;
    }

    public GenerateTestRequest setCustomIds(List<String> customIds) {
        this.customIds = customIds;
        return this;
    }

    public boolean isRandomHiragana() {
        return isRandomHiragana;
    }

    public GenerateTestRequest setRandomHiragana(boolean randomHiragana) {
        isRandomHiragana = randomHiragana;
        return this;
    }

    public boolean isRandomKatakana() {
        return isRandomKatakana;
    }

    public GenerateTestRequest setRandomKatakana(boolean randomKatakana) {
        isRandomKatakana = randomKatakana;
        return this;
    }

    public boolean isRandomCustom() {
        return isRandomCustom;
    }

    public GenerateTestRequest setRandomCustom(boolean randomCustom) {
        isRandomCustom = randomCustom;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GenerateTestRequest setTitle(String title) {
        this.title = title;
        return this;
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

        public Builder withRandomHiragana(boolean randomHiragana) {
            isRandomHiragana = randomHiragana;
            return this;
        }

        public Builder withRandomKatakana(boolean randomKatakana) {
            isRandomKatakana = randomKatakana;
            return this;
        }

        public Builder withRandomCustom(boolean randomCustom) {
            isRandomCustom = randomCustom;
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
