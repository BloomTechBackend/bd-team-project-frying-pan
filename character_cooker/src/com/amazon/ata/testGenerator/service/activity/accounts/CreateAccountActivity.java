package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Status;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.CreateAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.CreateAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateAccountActivity implements RequestHandler<CreateAccountRequest, CreateAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public CreateAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

//    public CreateAccountActivity() {
//        this.accountDao = new AccountDao(new DynamoDBMapper((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build()));
//    }

    /**
     * This method handles the incoming request by creating a new account
     * with the provided username and password from the request
     *
     * It returns a success message of the completion
     *
     * If the provided username or password has invalid characters, then throws an
     * InvalidAttributeValueException
     *
     * If the password and confirmation password do not match, then throws an
     * InvalidAttributeValueException
     *
     * If the username is already taken, then throw an InvalidAttributeValueException
     *
     * @param request The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public CreateAccountResult handleRequest(final CreateAccountRequest request, Context context) {
        log.info("Received CreateAccountRequest {}", request);

        // Validate username
        if(!TestGeneratorServiceUtils.isValidUsername(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }
        // Validate password
        if(!TestGeneratorServiceUtils.isValidPassword(request.getPassword())) {
            throw new InvalidAttributeValueException("Invalid Password");
        }
        // Validate Unique Username
        if (!accountDao.isIdUnique(request.getUsername())) {
            throw new InvalidAttributeValueException("Username: "
                    + request.getUsername() + " already exists!");
        }

        // Confirm Password
        if(!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new InvalidAttributeValueException("Passwords does not match!");
        }

        // Populate account object
        Account newAccount = new Account();
        newAccount.setUsername(request.getUsername());
        newAccount.setPassword(request.getPassword());
        newAccount.setStatus(Status.LOGGED_OUT.toString());

        accountDao.saveAccount(newAccount);

        return CreateAccountResult.builder()
                .withLogMessage("Create Account Successful!")
                .withUsername(newAccount.getUsername())
                .build();
    }
}
