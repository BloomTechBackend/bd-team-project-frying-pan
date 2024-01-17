package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.models.accounts.requests.CreateAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.CreateAccountResult;
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

    @Override
    public CreateAccountResult handleRequest(final CreateAccountRequest request, Context context) {
        log.info("Received CreateAccountRequest {}", request);




        return null;
    }
}
