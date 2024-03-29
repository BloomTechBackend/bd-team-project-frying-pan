package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.exceptions.TermNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class TermDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TermDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Term getTerm(String termId) {
        Term term = this.dynamoDBMapper.load(Term.class, termId);

        if (term == null) {
            throw new TermNotFoundException("{termId: " + termId + "} Not fond");
        }

        return term;
    }

    public boolean isIdUnique(String termId) {
        Term term = this.dynamoDBMapper.load(Term.class, termId);
        return term == null;
    }

    public void saveTerm(Term term) {
        this.dynamoDBMapper.save(term);
    }

    public void deleteTerm(Term term) {
        this.dynamoDBMapper.delete(term);
    }

    public List<Term> getTermsByTemplate(String templateId) {
        DynamoDBQueryExpression<Term> queryExpression = new DynamoDBQueryExpression<Term>()
                .withIndexName(Term.TEMPLATE_DATE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("templateId = :templateId") // Define the key condition expression
                .withExpressionAttributeValues(Map.of(":templateId", new AttributeValue().withS(templateId)));


        return dynamoDBMapper.query(Term.class, queryExpression);
    }

    public List<Term> getTermsByUser(String username) {
        DynamoDBQueryExpression<Term> queryExpression = new DynamoDBQueryExpression<Term>()
                .withIndexName(Term.USERNAME_DATE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("username = :username") // Define the key condition expression
                .withExpressionAttributeValues(Map.of(":username", new AttributeValue().withS(username)));

        return dynamoDBMapper.query(Term.class, queryExpression);
    }

}
