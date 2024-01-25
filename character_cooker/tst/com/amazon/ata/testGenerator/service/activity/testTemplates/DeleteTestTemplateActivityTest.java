package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.helpers.TemplateTestHelper;
import com.amazon.ata.testGenerator.service.helpers.TermTestHelper;
import com.amazon.ata.testGenerator.service.models.terms.requests.DeleteCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.DeleteTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.DeleteTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteTestTemplateActivityTest {
    @Mock
    TestTemplateDao testTemplateDao;

    @Mock
    TermDao termDao;

    DeleteTestTemplateActivity deleteTestTemplateActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteTestTemplateActivity = new DeleteTestTemplateActivity(testTemplateDao, termDao);
    }

    // Success Run Tests:

    @Test
    public void handlerRequest_deleteTemplateWithNoTerms_returnsTemplate() {
        // GIVEN
        TestTemplate expectedTemplate = TemplateTestHelper.generateTemplate(1);
        expectedTemplate.setUsername("expectedUsername");
        expectedTemplate.setHiraganaIdList(new ArrayList<>());
        expectedTemplate.setKatakanaIdList(new ArrayList<>());

        String expectedTemplatedId = expectedTemplate.getTemplateId();

        DeleteTestTemplateRequest request = DeleteTestTemplateRequest.builder()
                .withTemplateId(expectedTemplatedId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplatedId)).thenReturn(expectedTemplate);
        when(termDao.getTermsByTemplate(expectedTemplatedId)).thenReturn(new ArrayList<>());

        // WHEN
        DeleteTestTemplateResult result = deleteTestTemplateActivity.handleRequest(request, null);

        // THEN
        verify(testTemplateDao).deleteTemplate(expectedTemplate);

        TemplateTestHelper.assertTestTemplateEqualTemplateModel(expectedTemplate, result.getTemplate());
        assertTrue(result.getTerms().isEmpty());
    }

    @Test
    public void handlerRequest_deleteTemplateWithWithTerms_returnsTemplateWithTerms() {
        // GIVEN
        List<String> expectedIds1 = Arrays.asList(TermTestHelper.ids);
        TestTemplate expectedTemplate = TemplateTestHelper.generateTemplate(1);
        expectedTemplate.setUsername("expectedUsername");
        expectedTemplate.setHiraganaIdList(expectedIds1);
        expectedTemplate.setKatakanaIdList(expectedIds1);

        String expectedTemplatedId = expectedTemplate.getTemplateId();

        DeleteTestTemplateRequest request = DeleteTestTemplateRequest.builder()
                .withTemplateId(expectedTemplatedId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplatedId)).thenReturn(expectedTemplate);
        when(termDao.getTermsByTemplate(expectedTemplatedId)).thenReturn(new ArrayList<>());

        // WHEN
        DeleteTestTemplateResult result = deleteTestTemplateActivity.handleRequest(request, null);

        // THEN
        verify(testTemplateDao).deleteTemplate(expectedTemplate);

        TemplateTestHelper.assertTestTemplateEqualTemplateModel(expectedTemplate, result.getTemplate());
        assertTrue(result.getTerms().isEmpty());
    }

    @Test
    public void handlerRequest_deleteTemplateWithWithCustomTerms_returnsTemplateWithCustomTerms() {
        // GIVEN
        List<String> expectedIds1 = Arrays.asList(TermTestHelper.ids);

        String expectedUsername = "expectedUsername";
        TestTemplate expectedTemplate = TemplateTestHelper.generateTemplate(1);
        expectedTemplate.setUsername("expectedUsername");
        expectedTemplate.setHiraganaIdList(expectedIds1);
        expectedTemplate.setKatakanaIdList(expectedIds1);

        String expectedTemplatedId = expectedTemplate.getTemplateId();

        List<Term> terms = TermTestHelper.generateTermsList(5
                , expectedTemplatedId, expectedUsername, TestGeneratorServiceUtils.getDate());


        DeleteTestTemplateRequest request = DeleteTestTemplateRequest.builder()
                .withTemplateId(expectedTemplatedId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplatedId)).thenReturn(expectedTemplate);
        when(termDao.getTermsByTemplate(expectedTemplatedId)).thenReturn(terms);

        // WHEN
        DeleteTestTemplateResult result = deleteTestTemplateActivity.handleRequest(request, null);

        // THEN
        verify(testTemplateDao).deleteTemplate(expectedTemplate);

        TemplateTestHelper.assertTestTemplateEqualTemplateModel(expectedTemplate, result.getTemplate());
        TermTestHelper.assertTermsEqualTermModels(terms, result.getTerms());
    }

}
