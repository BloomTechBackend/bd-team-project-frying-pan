package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.CreateAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.CreateAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;

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

    /**
     * This method handles the incoming request by creating a new account
     * with the provided username and password from the request
     *
     * It returns a success message of the completion
     *
     * If the provided username or password has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * If the password and confirmation password do not match, throws an
     * InvalidAttributeValueException
     *
     * @param request The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return
     */
    @Override
    public CreateAccountResult handleRequest(final CreateAccountRequest request, Context context) {
        log.info("Received CreateAccountRequest {}", request);

        // validate username
        if(!TestGeneratorServiceUtils.isValidString(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }

        // validate password
        if(!TestGeneratorServiceUtils.isValidString(request.getPassword())
        || !request.getPassword().equals(request.getPasswordConfirm())) {
            throw new InvalidAttributeValueException("Invalid Password");
        }

        // Populate account object
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());

        accountDao.saveAccount(account);

        return CreateAccountResult.builder()
                .withLogMessage("Account creation successful")
                .build();
    }
}
