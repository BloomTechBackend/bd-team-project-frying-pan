package com.amazon.ata.testGenerator.service.lambda.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.UpdateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.UpdateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateTestTemplateActivityProvider implements RequestHandler<UpdateTestTemplateRequest, UpdateTestTemplateResult> {

    public UpdateTestTemplateActivityProvider() {
    }

    @Override
    public UpdateTestTemplateResult handleRequest(UpdateTestTemplateRequest request, Context context) {
        return getDaggerServiceComponent().provideUpdateTestTemplateActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
