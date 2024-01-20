package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteTestTemplateActivity {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;
    private final TermDao termDao;

    @Inject
    public DeleteTestTemplateActivity(TestTemplateDao testTemplateDao, TermDao termDao) {
        this.testTemplateDao = testTemplateDao;
        this.termDao = termDao;
    }
}
