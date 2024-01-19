package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class LogOutAccountActivity {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;

    @Inject
    public LogOutAccountActivity(AccountDao accountDao) {
        this.accountDao = accountDao;
    }




}
