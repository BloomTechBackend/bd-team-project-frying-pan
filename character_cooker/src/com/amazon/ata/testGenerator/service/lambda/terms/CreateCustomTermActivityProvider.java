package com.amazon.ata.testGenerator.service.lambda.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.terms.requests.CreateCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.CreateCustomTermResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateCustomTermActivityProvider implements RequestHandler<CreateCustomTermRequest, CreateCustomTermResult> {

    public CreateCustomTermActivityProvider() {
    }

    @Override
    public CreateCustomTermResult handleRequest(CreateCustomTermRequest request, Context context) {
        return getDaggerServiceComponent().provideCreateCustomTermActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }

}
