package com.amazon.ata.testGenerator.service.lambda.tests;

import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TestModel;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestRequest;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class GenerateTestActivityProvider implements RequestHandler<GenerateTestRequest, GenerateTestResult> {

    public GenerateTestActivityProvider() {
    }

    @Override
    public GenerateTestResult handleRequest(GenerateTestRequest request, Context context) {
        return getDaggerServiceComponent().provideGenerateTestActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
