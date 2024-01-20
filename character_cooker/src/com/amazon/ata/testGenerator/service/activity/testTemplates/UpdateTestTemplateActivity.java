package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateTestTemplateActivity {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public UpdateTestTemplateActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }
}
