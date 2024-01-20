package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
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

public class DeleteCustomTermActivity implements RequestHandler<DeleteCustomTermRequest, DeleteCustomTermResult> {
    private final Logger log = LogManager.getLogger();
    private TermDao termDao;

    @Inject
    public DeleteCustomTermActivity(TermDao termDao) {
        this.termDao = termDao;
    }

    @Override
    public DeleteCustomTermResult handleRequest(DeleteCustomTermRequest request, Context context) {
        log.info("Received DeleteCustomTermRequest {}", request);

        Term term = termDao.getTerm(request.getTermId());

        termDao.deleteTerm(term);

        TermModel termModel = ModelConverter.toTermModel(term);
        
        return DeleteCustomTermResult.builder()
                .withTerm(termModel)
                .build();
    }
}
