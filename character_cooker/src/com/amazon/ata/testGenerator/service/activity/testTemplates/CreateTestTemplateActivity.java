package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.CreateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

public class CreateTestTemplateActivity implements RequestHandler<CreateTestTemplateRequest, CreateTestTemplateResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public CreateTestTemplateActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }

    @Override
    public CreateTestTemplateResult handleRequest(CreateTestTemplateRequest request, Context context) {
        log.info("Received CreateTestTemplateRequest {}", request);

        // Create Template
        TestTemplate template = new TestTemplate();
        template.setTitle(request.getTitle());
        template.setUsername(request.getUsername());
        template.setHiraganaIdList(request.getHiraganaIdList());
        template.setKatakanaIdList(request.getKatakanaIdList());
        template.setDateModified(TestGeneratorServiceUtils.getDate());

        // Create Unique Template ID
        String id;
        do {
            id = TestGeneratorServiceUtils.generateTemplateId();
        } while (!testTemplateDao.isIdUnique(id));
        template.setTemplateId(id);

        testTemplateDao.saveTemplate(template);

        // Convert to Model
        TemplateModel templateModel = ModelConverter.toTemplateModel(template);

        return CreateTestTemplateResult.builder()
                .withTemplate(templateModel)
                .build();
    }
}
