package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.GetTemplateByUsernameDateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTemplateByUsernameDateResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTemplateByUsernameDateActivity implements RequestHandler<GetTemplateByUsernameDateRequest,
        GetTemplateByUsernameDateResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public GetTemplateByUsernameDateActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }

    @Override
    public GetTemplateByUsernameDateResult handleRequest(GetTemplateByUsernameDateRequest request, Context context) {
        log.info("Received GetTemplateByUsernameDateRequest {}", request);

        List<TestTemplate> templates = testTemplateDao.getTemplateByUserDate(request.getUsername());

        List<TemplateModel> templateModels = new ArrayList<>();
        for (TestTemplate template : templates) {
            templateModels.add(ModelConverter.toTemplateModel(template));
        }

        return GetTemplateByUsernameDateResult.builder()
                .withTemplates(templateModels)
                .build();
    }
}
