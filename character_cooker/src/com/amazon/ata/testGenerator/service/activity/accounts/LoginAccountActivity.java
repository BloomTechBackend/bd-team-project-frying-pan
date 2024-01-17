package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.LoginAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.LoginAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class LoginAccountActivity implements RequestHandler<LoginAccountRequest, LoginAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public LoginAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public LoginAccountResult handleRequest(final LoginAccountRequest request, Context context) {
        log.info("Received LoginAccountRequest {}", request);

        // validate username value
        if(!TestGeneratorServiceUtils.isValidString(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }

        // validate password value
        if(!TestGeneratorServiceUtils.isValidString(request.getPassword())) {
            throw new InvalidAttributeValueException("Invalid Password");
        }

        // Load Account
        Account account = accountDao.getAccount(request.getUsername());

        // Check password
        if (request.getPassword().equals(account.getPassword())) {
            throw new InvalidAttributeValueException("Incorrect Password");
        }

        return LoginAccountResult.builder()
                .withLogMessage("Account login successful")
                .build();
    }
}
