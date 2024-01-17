package com.amazon.ata.testGenerator.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "LBC_Terms")
public class TestTemplate {
    private String templateId;
    private String title;
    private String username;
    private String dateModified;
    private List<String> termIdList;

    public TestTemplate() {}

    @DynamoDBHashKey(attributeName = "templateId")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UsernameTitleIndex", attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {"UsernameTitleIndex", "UsernameDateIndex"}
            , attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UsernameDateIndex", attributeName = "dateModified")
    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    @DynamoDBAttribute(attributeName = "termIdList")
    public List<String> getTermIdList() {
        return termIdList;
    }

    public void setTermIdList(List<String> termIdList) {
        this.termIdList = termIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTemplate that = (TestTemplate) o;
        return Objects.equals(templateId, that.templateId) && Objects.equals(title, that.title) && Objects.equals(username, that.username) && Objects.equals(dateModified, that.dateModified) && Objects.equals(termIdList, that.termIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, title, username, dateModified, termIdList);
    }
}
