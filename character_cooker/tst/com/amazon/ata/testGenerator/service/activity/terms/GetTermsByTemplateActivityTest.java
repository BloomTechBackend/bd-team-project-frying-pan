package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.helpers.TermTestHelper;
import com.amazon.ata.testGenerator.service.models.terms.requests.GetTermsByTemplateRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.GetTermsByTemplateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetTermsByTemplateActivityTest {
    @Mock
    private TermDao termDao;

    private GetTermsByTemplateActivity getTermsByTemplateActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getTermsByTemplateActivity = new GetTermsByTemplateActivity(termDao);
    }

    @Test
    public void handleRequest_templateWithCustomTerms_returnTermsFromTemplate() {
        // GIVEN
        String expectedUsername = "CandyApple";
        String expectTemplateId = "CAt001";
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<Term> expectedTerms = TermTestHelper.generateTermsList(5, expectTemplateId, expectedUsername, expectedDate);

        GetTermsByTemplateRequest request = GetTermsByTemplateRequest.builder()
                .withTemplateId(expectTemplateId)
                .build();

        when(termDao.getTermsByTemplate(request.getTemplateId())).thenReturn(expectedTerms);

        // WHEN
        GetTermsByTemplateResult result = getTermsByTemplateActivity.handleRequest(request, null);

        // THEN
        TermTestHelper.assertTermsEqualTermModels(expectedTerms, result.getTerms());

    }

    @Test
    public void handleRequest_templateWithoutCustomTerms_returnEmptyList() {
        // GIVEN
        String expectedUsername = "CandyApple";
        String expectTemplateId = "CAt001";
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<Term> expectedTerms = TermTestHelper.generateTermsList(0, expectTemplateId, expectedUsername, expectedDate);

        GetTermsByTemplateRequest request = GetTermsByTemplateRequest.builder()
                .withTemplateId(expectTemplateId)
                .build();

        when(termDao.getTermsByTemplate(request.getTemplateId())).thenReturn(expectedTerms);

        // WHEN
        GetTermsByTemplateResult result = getTermsByTemplateActivity.handleRequest(request, null);

        // THEN
        assertTrue(result.getTerms().isEmpty());
    }
}
