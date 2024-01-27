package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.exceptions.TestTemplateNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTemplateDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TestTemplateDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public TestTemplate getTemplate(String templateId) {
        TestTemplate template = this.dynamoDBMapper.load(TestTemplate.class, templateId);
        if (template == null) {
            throw new TestTemplateNotFoundException("{testTemplateId: " + templateId + "} Not fond");
        }
        return template;
    }

    public boolean isIdUnique(String templateId) {
        TestTemplate template = this.dynamoDBMapper.load(TestTemplate.class, templateId);
        return template == null;
    }

    public TestTemplate saveTemplate(TestTemplate template) {
        this.dynamoDBMapper.save(template);
        return template;
    }

    public void deleteTemplate(TestTemplate template) {
        this.dynamoDBMapper.delete(template);
    }

    public List<TestTemplate> getTemplateByUserTitle (String username) {
        DynamoDBQueryExpression<TestTemplate> queryExpression = new DynamoDBQueryExpression<TestTemplate>()
                .withIndexName(TestTemplate.USERNAME_TITLE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("username = :username") // Define the key condition expression
                .withExpressionAttributeValues(Map.of(":username", new AttributeValue().withS(username)));

        return dynamoDBMapper.query(TestTemplate.class, queryExpression);
    }

    public List<TestTemplate> getTemplateByUserDate (String username) {
        TestTemplate template = new TestTemplate();
        template.setUsername(username);
        DynamoDBQueryExpression<TestTemplate> queryExpression = new DynamoDBQueryExpression<TestTemplate>()
                .withIndexName(TestTemplate.USERNAME_DATE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("username = :username") // Define the key condition expression
                .withExpressionAttributeValues(Map.of(":username", new AttributeValue().withS(username)));

        return dynamoDBMapper.query(TestTemplate.class, queryExpression);
    }
}
