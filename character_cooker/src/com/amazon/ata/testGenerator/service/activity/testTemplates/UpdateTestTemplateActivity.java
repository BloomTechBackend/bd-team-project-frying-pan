package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.UpdateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.UpdateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateTestTemplateActivity implements RequestHandler<UpdateTestTemplateRequest, UpdateTestTemplateResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public UpdateTestTemplateActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }

    @Override
    public UpdateTestTemplateResult handleRequest(UpdateTestTemplateRequest request, Context context) {
        log.info("Received UpdateTestTemplateRequest {}", request);

        // Create Template
        TestTemplate template = testTemplateDao.getTemplate(request.getTemplateId());
        template.setTitle(request.getTitle());
        template.setUsername(request.getUsername());
        template.setHiraganaIdList(request.getHiraganaIdList());
        template.setKatakanaIdList(request.getKatakanaIdList());
        template.setDateModified(TestGeneratorServiceUtils.getDate());

        template = testTemplateDao.saveTemplate(template);

        // Convert to Model
        TemplateModel templateModel = ModelConverter.toTemplateModel(template);

        return UpdateTestTemplateResult.builder()
                .withTemplate(templateModel)
                .build();
    }
}
