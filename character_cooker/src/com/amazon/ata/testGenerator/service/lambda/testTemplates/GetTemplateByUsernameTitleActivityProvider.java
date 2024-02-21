package com.amazon.ata.testGenerator.service.lambda.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
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

public class GetTemplateByUsernameTitleActivityProvider implements RequestHandler<GetTemplateByUsernameTitleRequest,
        GetTemplateByUsernameTitleResult> {

    public GetTemplateByUsernameTitleActivityProvider() {
    }

    @Override
    public GetTemplateByUsernameTitleResult handleRequest(GetTemplateByUsernameTitleRequest request, Context context) {
        return getDaggerServiceComponent().provideGetTemplateByUsernameTitleActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
