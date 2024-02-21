package com.amazon.ata.testGenerator.service.lambda.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.terms.requests.DeleteCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.DeleteCustomTermResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteCustomTermActivityProvider implements RequestHandler<DeleteCustomTermRequest, DeleteCustomTermResult> {

    public DeleteCustomTermActivityProvider() {
    }

    @Override
    public DeleteCustomTermResult handleRequest(DeleteCustomTermRequest request, Context context) {
        return getDaggerServiceComponent().provideDeleteCustomTermActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
