package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

public class TestTemplateDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TestTemplateDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public TestTemplate getTerm(String templateId) {
        TestTemplate template = this.dynamoDBMapper.load(TestTemplate.class, templateId);

        if (template == null) {
            throw new RuntimeException("{testTemplateId: " + templateId + "} Not fond");
        }

        return template;
    }

    public void saveTemplate(TestTemplate template) {

    }

    public void deleteTemplate(String templateId) {

    }
}
