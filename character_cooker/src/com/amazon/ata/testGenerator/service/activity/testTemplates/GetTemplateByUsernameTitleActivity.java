package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.GetTemplateByUsernameTitleRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTemplateByUsernameTitleResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTemplateByUsernameTitleActivity implements RequestHandler<GetTemplateByUsernameTitleRequest,
        GetTemplateByUsernameTitleResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;

    @Inject
    public GetTemplateByUsernameTitleActivity(TestTemplateDao testTemplateDao) {
        this.testTemplateDao = testTemplateDao;
    }

    @Override
    public GetTemplateByUsernameTitleResult handleRequest(GetTemplateByUsernameTitleRequest request, Context context) {
        log.info("Received GetTemplateByUsernameTitleRequest {}", request);

        List<TestTemplate> templates = testTemplateDao.getTemplateByUserTitle(request.getUsername());

        List<TemplateModel> templateModels = new ArrayList<>();
        for (TestTemplate template : templates) {
            templateModels.add(ModelConverter.toTemplateModel(template));
        }

        return GetTemplateByUsernameTitleResult.builder()
                .withTemplates(templateModels)
                .build();
    }
}
