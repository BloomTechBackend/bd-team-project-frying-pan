package com.amazon.ata.testGenerator.service.models.tests;

import com.amazon.ata.testGenerator.service.models.TestModel;

import java.util.Objects;

public class GenerateTestResult {
    private String title;
    private TestModel hiraganaTest;
    private TestModel katakanaTest;
    private TestModel customTest;

    public GenerateTestResult(Builder builder) {
        this.title = builder.title;
        this.hiraganaTest = builder.hiraganaTest;
        this.katakanaTest = builder.katakanaTest;
        this.customTest = builder.customTest;
    }

    public GenerateTestResult() {
    }

    public String getTitle() {
        return title;
    }

    public GenerateTestResult setTitle(String title) {
        this.title = title;
        return this;
    }

    public TestModel getHiraganaTest() {
        return hiraganaTest;
    }

    public GenerateTestResult setHiraganaTest(TestModel hiraganaTest) {
        this.hiraganaTest = hiraganaTest;
        return this;
    }

    public TestModel getKatakanaTest() {
        return katakanaTest;
    }

    public GenerateTestResult setKatakanaTest(TestModel katakanaTest) {
        this.katakanaTest = katakanaTest;
        return this;
    }

    public TestModel getCustomTest() {
        return customTest;
    }

    public GenerateTestResult setCustomTest(TestModel customTest) {
        this.customTest = customTest;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerateTestResult that = (GenerateTestResult) o;
        return Objects.equals(title, that.title) && Objects.equals(hiraganaTest, that.hiraganaTest) && Objects.equals(katakanaTest, that.katakanaTest) && Objects.equals(customTest, that.customTest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, hiraganaTest, katakanaTest, customTest);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private TestModel hiraganaTest;
        private TestModel katakanaTest;
        private TestModel customTest;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withHiraganaTest(TestModel hiraganaTest) {
            this.hiraganaTest = hiraganaTest;
            return this;
        }

        public Builder withKatakanaTest(TestModel katakanaTest) {
            this.katakanaTest = katakanaTest;
            return this;
        }

        public Builder withCustomTest(TestModel customTest) {
            this.customTest = customTest;
            return this;
        }

        public GenerateTestResult build() {
            return new GenerateTestResult(this);
        }
    }
}
