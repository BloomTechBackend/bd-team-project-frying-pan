package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.TestModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.DeleteTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.DeleteTestTemplateResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DeleteTestTemplateActivity implements RequestHandler<DeleteTestTemplateRequest, DeleteTestTemplateResult> {
    private final Logger log = LogManager.getLogger();
    private final TestTemplateDao testTemplateDao;
    private final TermDao termDao;

    @Inject
    public DeleteTestTemplateActivity(TestTemplateDao testTemplateDao, TermDao termDao) {
        this.testTemplateDao = testTemplateDao;
        this.termDao = termDao;
    }


    @Override
    public DeleteTestTemplateResult handleRequest(DeleteTestTemplateRequest request, Context context) {

        TestTemplate template = testTemplateDao.getTemplate(request.getTemplateId());
        testTemplateDao.deleteTemplate(template);
        TemplateModel templateModel = ModelConverter.toTemplateModel(template);

        List<Term> terms = termDao.getTermsByTemplate(request.getTemplateId());

        List<TermModel> termModels = new ArrayList<>();

        for (Term term : terms) {
            termDao.deleteTerm(term);
            termModels.add(ModelConverter.toTermModel(term));
        }

        return DeleteTestTemplateResult.builder()
                .withTemplate(templateModel)
                .withTerms(termModels)
                .build();
    }
}
