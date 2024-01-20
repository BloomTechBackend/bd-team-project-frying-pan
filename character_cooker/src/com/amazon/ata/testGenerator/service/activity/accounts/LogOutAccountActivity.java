package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Status;
import com.amazon.ata.testGenerator.service.models.accounts.requests.LogOutAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.LogOutAccountResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class LogOutAccountActivity implements RequestHandler<LogOutAccountRequest, LogOutAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public LogOutAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public LogOutAccountResult handleRequest(LogOutAccountRequest request, Context context) {
        log.info("Received LogOutAccountRequest {}", request);

        Account account = accountDao.getAccount(request.getUsername());

        account.setStatus(Status.LOGGED_OUT.toString());

        accountDao.saveAccount(account);

        return new LogOutAccountResult.Builder()
                .withLogMessage("Log Out Successful!")
                .withUsername(account.getUsername())
                .withStatus(account.getStatus())
                .build();
    }
}
