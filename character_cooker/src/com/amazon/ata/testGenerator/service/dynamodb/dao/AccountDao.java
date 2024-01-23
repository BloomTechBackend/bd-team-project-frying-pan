package com.amazon.ata.testGenerator.service.dynamodb.dao;

import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Status;
import com.amazon.ata.testGenerator.service.exceptions.AccountNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.UnauthorizedAccessException;
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
            throw new AccountNotFoundException("{Username: " + username + "} Not fond");
        }

        return account;
    }

    public boolean isLoggedIn(String username) {
        Account account = this.dynamoDBMapper.load(Account.class, username);

        if (account == null) {
            throw new AccountNotFoundException("{Username: " + username + "} Not fond");
        }

        if (account.getStatus().equals(Status.LOGGED_IN.toString())) {
            return true;
        } else {
            throw new UnauthorizedAccessException("User access denied. \nPlease log in to access this feature.");
        }

    }

    public void saveAccount(Account account) {
        this.dynamoDBMapper.save(account);
    }

    public void deleteAccount(Account account) {
        this.dynamoDBMapper.delete(account);
    }
}
