package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

public class AccountDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public AccountDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Account getAccount(String username) {
        Account account = this.dynamoDBMapper.load(Account.class, username);

        if (account == null) {
            throw new RuntimeException("{Username: " + username + "} Not fond");
        }

        return account;
    }

    public void saveAccount(Account account) {
        this.dynamoDBMapper.save(account);
    }

    public void deleteAccount(Account account) {
        this.dynamoDBMapper.delete(account);
    }
}
