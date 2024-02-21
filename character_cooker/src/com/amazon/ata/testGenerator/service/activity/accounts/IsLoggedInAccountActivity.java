package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.IsLoggedInAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.IsLoggedInAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class IsLoggedInAccountActivity implements RequestHandler<IsLoggedInAccountRequest, IsLoggedInAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public IsLoggedInAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public IsLoggedInAccountResult handleRequest(IsLoggedInAccountRequest request, Context context) {
        log.info("Received IsLoggedInAccountRequest {}", request);

        if(!TestGeneratorServiceUtils.isValidUsername(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }

        boolean isLoggedIn = accountDao.isLoggedIn(request.getUsername());

        return IsLoggedInAccountResult.builder()
                .withUsername(request.getUsername())
                .withLoggedIn(isLoggedIn)
                .build();
    }
}
