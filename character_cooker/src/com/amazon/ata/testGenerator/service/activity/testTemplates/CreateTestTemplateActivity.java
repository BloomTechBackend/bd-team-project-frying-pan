package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.CreateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateTestTemplateActivity implements RequestHandler<CreateTestTemplateRequest, CreateTestTemplateResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;
    private final AccountDao accountDao;

    @Inject
    public CreateTestTemplateActivity(TestTemplateDao testTemplateDao, AccountDao accountDao) {
        this.testTemplateDao = testTemplateDao;
        this.accountDao = accountDao;
    }

    @Override
    public CreateTestTemplateResult handleRequest(CreateTestTemplateRequest request, Context context) {
        // validate users


        return null;
    }
}
