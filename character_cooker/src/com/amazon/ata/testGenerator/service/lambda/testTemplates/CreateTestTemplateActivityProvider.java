package com.amazon.ata.testGenerator.service.lambda.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.CreateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateTestTemplateActivityProvider implements RequestHandler<CreateTestTemplateRequest, CreateTestTemplateResult> {

    public CreateTestTemplateActivityProvider() {
    }

    @Override
    public CreateTestTemplateResult handleRequest(CreateTestTemplateRequest request, Context context) {
        return getDaggerServiceComponent().provideCreateTestTemplateActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
