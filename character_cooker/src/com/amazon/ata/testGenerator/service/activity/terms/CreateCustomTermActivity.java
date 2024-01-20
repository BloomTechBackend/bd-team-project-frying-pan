package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
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

public class CreateCustomTermActivity implements RequestHandler<CreateCustomTermRequest, CreateCustomTermResult> {
    private final Logger log = LogManager.getLogger();
    private TermDao termDao;

    @Inject
    public CreateCustomTermActivity(TermDao termDao) {
        this.termDao = termDao;
    }

    @Override
    public CreateCustomTermResult handleRequest(CreateCustomTermRequest request, Context context) {
        log.info("Received CreateCustomTermRequest {}", request);

        Term term = new Term();
        term.setRomanization(request.getRomanization());
        term.setSymbol(request.getSymbol());
        term.setUsername(request.getUsername());
        term.setTemplateId(request.getTemplateId());
        term.setDateCreated(TestGeneratorServiceUtils.getDate());

        // Generate Unique Term Id
        String id;
        do {
            id = TestGeneratorServiceUtils.generateTemplateId();
        } while (!termDao.isIdUnique(id));
        term.setTermId(id);

        termDao.saveTerm(term);

        TermModel termModel = ModelConverter.toTermModel(term);

        return CreateCustomTermResult.builder()
                .withTerm(termModel)
                .build();
    }


}
