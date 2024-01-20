package com.amazon.ata.testGenerator.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "LBC_Terms")
public class TestTemplate {
    public static final String USERNAME_TITLE_INDEX= "UsernameTitleIndex";
    public static final String USERNAME_DATE_INDEX = "UsernameDateIndex";

    private String templateId;
    private String title;
    private String username;
    private String dateModified;
    private List<String> hiraganaIdList;
    private List<String> katakanaIdList;

    public TestTemplate() {}

    @DynamoDBHashKey(attributeName = "templateId")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = USERNAME_TITLE_INDEX, attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {USERNAME_TITLE_INDEX, USERNAME_DATE_INDEX}
            , attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = USERNAME_DATE_INDEX, attributeName = "dateModified")
    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    @DynamoDBAttribute(attributeName = "hiraganaIdList")
    public java.util.List<String> getHiraganaIdList() {
        return hiraganaIdList;
    }

    public TestTemplate setHiraganaIdList(java.util.List<String> hiraganaIdList) {
        this.hiraganaIdList = hiraganaIdList;
        return this;
    }

    @DynamoDBAttribute(attributeName = "katakanaIdList")
    public java.util.List<String> getKatakanaIdList() {
        return katakanaIdList;
    }

    public TestTemplate setKatakanaIdList(java.util.List<String> katakanaIdList) {
        this.katakanaIdList = katakanaIdList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTemplate that = (TestTemplate) o;
        return Objects.equals(templateId, that.templateId)
                && Objects.equals(title, that.title)
                && Objects.equals(username, that.username)
                && Objects.equals(dateModified, that.dateModified)
                && Objects.equals(hiraganaIdList, that.hiraganaIdList)
                && Objects.equals(katakanaIdList, that.katakanaIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, title, username, dateModified, hiraganaIdList, katakanaIdList);
    }
}
