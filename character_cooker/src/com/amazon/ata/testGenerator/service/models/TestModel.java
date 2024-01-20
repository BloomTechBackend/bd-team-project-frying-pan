package com.amazon.ata.testGenerator.service.models;

import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;

import java.util.Objects;

public class TestModel {
    private String title;
    private String testQuestions;
    private String testAnswers;

    public TestModel() {

    }

    public TestModel(Builder builder) {
        this.title = builder.title;
        this.testQuestions = builder.testQuestions;
        this.testAnswers = builder.testAnswers;
    }

    public String getTitle() {
        return title;
    }

    public TestModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTestQuestions() {
        return testQuestions;
    }

    public TestModel setTestQuestions(String testQuestions) {
        this.testQuestions = testQuestions;
        return this;
    }

    public String getTestAnswers() {
        return testAnswers;
    }

    public TestModel setTestAnswers(String testAnswers) {
        this.testAnswers = testAnswers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel testModel = (TestModel) o;
        return Objects.equals(title, testModel.title) && Objects.equals(testQuestions, testModel.testQuestions) && Objects.equals(testAnswers, testModel.testAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, testQuestions, testAnswers);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String testQuestions;
        private String testAnswers;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withTestQuestions(String testQuestions) {
            this.testQuestions = testQuestions;
            return this;
        }

        public Builder withTestAnswers(String testAnswers) {
            this.testAnswers = testAnswers;
            return this;
        }

        public TestModel build() {
            return new TestModel(this);
        }
    }
}
