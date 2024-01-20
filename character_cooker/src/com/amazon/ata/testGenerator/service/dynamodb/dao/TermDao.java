package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import javax.inject.Inject;
import java.util.List;

public class TermDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TermDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Term getTerm(String termId) {
        Term term = this.dynamoDBMapper.load(Term.class, termId);

        if (term == null) {
            throw new RuntimeException("{termId: " + termId + "} Not fond");
        }

        return term;
    }

    public boolean isIdUnique(String termId) {
        Term term = this.dynamoDBMapper.load(Term.class, termId);
        return term == null;
    }

    public Term saveTerm(Term term) {
        this.dynamoDBMapper.save(term);
        return term;
    }

    public void deleteTerm(Term term) {
        this.dynamoDBMapper.delete(term);
    }

    public List<Term> getTermsByUser(String username) {
        Term term = new Term();
        term.setUsername(username);
        DynamoDBQueryExpression<Term> queryExpression = new DynamoDBQueryExpression<Term>()
                .withIndexName(Term.USERNAME_DATE_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(term);

        return dynamoDBMapper.query(Term.class, queryExpression);
    }

    public List<Term> getTermsByTemplate(String templateId) {
        Term term = new Term();
        term.setUsername(templateId);
        DynamoDBQueryExpression<Term> queryExpression = new DynamoDBQueryExpression<Term>()
                .withIndexName(Term.TEMPLATE_DATE_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(term);

        return dynamoDBMapper.query(Term.class, queryExpression);
    }


}
