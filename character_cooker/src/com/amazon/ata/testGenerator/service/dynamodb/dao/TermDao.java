package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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

    public Term saveTerm(Term term) {
        this.dynamoDBMapper.save(term);
        return term;
    }

    public void deleteTerm(Term term) {
        this.dynamoDBMapper.delete(term);
    }

    public List<Term> getTermsByUser(String username) {
        return null;
    }

    public List<Term> getTermsByTemplate(String templateId) {
        return null;
    }


}
