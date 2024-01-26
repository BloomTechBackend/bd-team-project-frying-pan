package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.TermModel;
import com.amazon.ata.testGenerator.service.models.accounts.requests.DeleteAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.DeleteAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DeleteAccountActivity implements RequestHandler<DeleteAccountRequest, DeleteAccountResult> {
    private final Logger log = LogManager.getLogger();
    private final AccountDao accountDao;
    private final TestTemplateDao testTemplateDao;
    private final TermDao termDao;

    @Inject
    public DeleteAccountActivity(AccountDao accountDao, TestTemplateDao testTemplateDao, TermDao termDao) {
        this.accountDao = accountDao;
        this.testTemplateDao = testTemplateDao;
        this.termDao = termDao;
    }

    @Inject

    /**
     *
     * @param request The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return DeleteAccountResult
     */
    @Override
    public DeleteAccountResult handleRequest(final DeleteAccountRequest request, Context context) {
        log.info("Received DeleteAccountRequest {}", request);
        // validate username
        if(!TestGeneratorServiceUtils.isValidUsername(request.getUsername())) {
            throw new InvalidAttributeValueException("Invalid Username!");
        }
        // validate password
        if(!TestGeneratorServiceUtils.isValidUsername(request.getPassword())) {
            throw new InvalidAttributeValueException("Invalid Password");
        }
        // Populate account primary key
        Account account = accountDao.getAccount(request.getUsername());
        // Check password
        if (request.getPassword().equals(account.getPassword())) {
            throw new InvalidAttributeValueException("Incorrect Password");
        }
        accountDao.deleteAccount(account);

        List<TestTemplate> templates = testTemplateDao.getTemplateByUserTitle(request.getUsername());
        List<TemplateModel> templateModels = new ArrayList<>();
        for (TestTemplate template : templates) {
            testTemplateDao.deleteTemplate(template);
            templateModels.add(ModelConverter.toTemplateModel(template));
        }

        List<Term> terms = termDao.getTermsByUser(request.getUsername());
        List<TermModel> termModels = new ArrayList<>();
        for (Term term : terms) {
            termDao.deleteTerm(term);
            termModels.add(ModelConverter.toTermModel(term));
        }

        return DeleteAccountResult.builder()
                .withLogMessage("Account deletion successful")
                .withUsername(request.getUsername())
                .withTemplatesDeleted(templateModels)
                .withTermsDeleted(termModels)
                .build();
    }
}
