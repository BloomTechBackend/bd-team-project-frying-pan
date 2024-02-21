package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.GetTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTestTemplateResult;
import com.amazonaws.Request;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetTestTemplateActivity implements RequestHandler <GetTestTemplateRequest, GetTestTemplateResult>{
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public GetTestTemplateActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }

    @Override
    public GetTestTemplateResult handleRequest(GetTestTemplateRequest request, Context context) {
        log.info("Received GetTestTemplateRequest {}", request);

        TestTemplate template = testTemplateDao.getTemplate(request.getTemplateId());

        TemplateModel templateModel = ModelConverter.toTemplateModel(template);

        return GetTestTemplateResult.builder()
                .withTemplate(templateModel)
                .build();
    }
}
