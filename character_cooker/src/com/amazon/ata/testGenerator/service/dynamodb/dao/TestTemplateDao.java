package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import javax.inject.Inject;

import java.util.List;

public class TestTemplateDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TestTemplateDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public TestTemplate getTemplate(String templateId) {
        TestTemplate template = this.dynamoDBMapper.load(TestTemplate.class, templateId);
        if (template == null) {
            throw new RuntimeException("{testTemplateId: " + templateId + "} Not fond");
        }
        return template;
    }

    public boolean isIdUnique(String templateId) {
        TestTemplate template = this.dynamoDBMapper.load(TestTemplate.class, templateId);
        return template == null;
    }

    public void saveTemplate(TestTemplate template) {
        this.dynamoDBMapper.save(template);
    }

    public void deleteTemplate(TestTemplate template) {
        this.dynamoDBMapper.delete(template);
    }

    public List<TestTemplate> getTemplateByUserTitle (String username) {
        TestTemplate template = new TestTemplate();
        template.setUsername(username);
        DynamoDBQueryExpression<TestTemplate> queryExpression = new DynamoDBQueryExpression<TestTemplate>()
                .withIndexName(TestTemplate.USERNAME_TITLE_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(template);

        return dynamoDBMapper.query(TestTemplate.class, queryExpression);
    }

    public List<TestTemplate> getTemplateByUserDate (String username) {
        TestTemplate template = new TestTemplate();
        template.setUsername(username);
        DynamoDBQueryExpression<TestTemplate> queryExpression = new DynamoDBQueryExpression<TestTemplate>()
                .withIndexName(TestTemplate.USERNAME_DATE_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(template);

        return dynamoDBMapper.query(TestTemplate.class, queryExpression);
    }
}
