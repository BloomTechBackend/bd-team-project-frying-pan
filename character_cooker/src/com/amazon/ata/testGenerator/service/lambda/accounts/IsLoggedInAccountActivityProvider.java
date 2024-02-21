package com.amazon.ata.testGenerator.service.lambda.accounts;

import com.amazon.ata.testGenerator.service.dependency.DaggerServiceComponent;
import com.amazon.ata.testGenerator.service.dependency.ServiceComponent;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.IsLoggedInAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.IsLoggedInAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class IsLoggedInAccountActivityProvider implements RequestHandler<IsLoggedInAccountRequest, IsLoggedInAccountResult> {
    public IsLoggedInAccountActivityProvider() {
    }

    @Override
    public IsLoggedInAccountResult handleRequest(IsLoggedInAccountRequest request, Context context) {
        return getDaggerServiceComponent().provideIsLoggedInAccountActivity().handleRequest(request, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        return DaggerServiceComponent.create();
    }
}
