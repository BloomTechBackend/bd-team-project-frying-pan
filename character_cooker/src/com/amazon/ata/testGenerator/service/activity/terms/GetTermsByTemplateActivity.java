package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.terms.requests.CreateCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.terms.requests.GetTermsByTemplateRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.CreateCustomTermResult;
import com.amazon.ata.testGenerator.service.models.terms.results.GetTermsByTemplateResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetTermsByTemplateActivity implements RequestHandler<GetTermsByTemplateRequest, GetTermsByTemplateResult> {
    private final Logger log = LogManager.getLogger();
    private TermDao termDao;

    @Inject
    public GetTermsByTemplateActivity(TermDao termDao) {
        this.termDao = termDao;
    }

    @Override
    public GetTermsByTemplateResult handleRequest(GetTermsByTemplateRequest request, Context context) {

        List<Term> terms = termDao.getTermsByTemplate(request.getTemplateId());

        List<TermModel> termModels = new ArrayList<>();

        for (Term term : terms) {
            termModels.add(ModelConverter.toTermModel(term));
        }

        return GetTermsByTemplateResult.builder()
                .withTerms(termModels)
                .build();
    }
}
