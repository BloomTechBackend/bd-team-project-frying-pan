package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.DeleteAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.DeleteAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteAccountActivity implements RequestHandler<DeleteAccountRequest, DeleteAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public DeleteAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public DeleteAccountResult handleRequest(final DeleteAccountRequest request, Context context) {
        log.info("Received DeleteAccountRequest {}", request);

        // validate username
        if(!TestGeneratorServiceUtils.isValidUsername(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }

        // validate password
        if(!TestGeneratorServiceUtils.isValidUsername(request.getPassword())) {
            throw new InvalidAttributeValueException("Invalid Password");
        }

        // Populate account primary key
        Account account = accountDao.getAccount(request.getUsername());

        // Check password
        if (request.getPassword().equals(account.getPassword())) {
            throw new InvalidAttributeValueException("Incorrect Password");
        }

        accountDao.deleteAccount(account);

        return DeleteAccountResult.builder()
                .withLogMessage("Account deletion successful")
                .build();
    }
}
