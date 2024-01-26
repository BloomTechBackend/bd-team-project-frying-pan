package com.amazon.ata.testGenerator.service.lambda.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.terms.requests.GetTermsByUsernameRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.GetTermsByUsernameResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTermsByUsernameActivityProvider implements RequestHandler<GetTermsByUsernameRequest, GetTermsByUsernameResult> {
    private final Logger log = LogManager.getLogger();
    private TermDao termDao;

    @Inject
    public GetTermsByUsernameActivityProvider(TermDao termDao) {
        this.termDao = termDao;
    }

    @Override
    public GetTermsByUsernameResult handleRequest(GetTermsByUsernameRequest request, Context context) {
        return getDaggerServiceComponent().provideGetTermsByUsernameActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
