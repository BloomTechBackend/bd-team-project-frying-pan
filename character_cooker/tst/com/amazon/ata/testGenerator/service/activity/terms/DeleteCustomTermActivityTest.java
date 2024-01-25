package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.exceptions.TermNotFoundException;
import com.amazon.ata.testGenerator.service.helpers.TermTestHelper;
import com.amazon.ata.testGenerator.service.models.terms.requests.DeleteCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.terms.requests.GetTermsByTemplateRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.DeleteCustomTermResult;
import com.amazon.ata.testGenerator.service.models.terms.results.GetTermsByTemplateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteCustomTermActivityTest {
    @Mock
    private TermDao termDao;

    private DeleteCustomTermActivity deleteCustomTerm;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteCustomTerm = new DeleteCustomTermActivity(termDao);
    }

    @Test
    public void handleRequest_CustomTermExists_returnDeletedCustomTerm() {
        // GIVEN
        String expectedRomaji = "aka";
        String expectedSymbol = "あか";
        String expectedTermId = "H0001";
        String expectedUsername = "CandyApple";
        String expectTemplateId = "CAt001";
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Term term = new Term();
        term.setTermId(expectedTermId);
        term.setRomanization(expectedRomaji);
        term.setSymbol(expectedSymbol);
        term.setTemplateId(expectTemplateId);
        term.setUsername(expectedUsername);
        term.setDateCreated(expectedDate);

        DeleteCustomTermRequest request = DeleteCustomTermRequest.builder()
                .withTermId(expectedTermId)
                .build();

        when(termDao.getTerm(request.getTermId())).thenReturn(term);

        // WHEN
        DeleteCustomTermResult result = deleteCustomTerm.handleRequest(request, null);

        // THEN
        verify(termDao).deleteTerm(term);

        TermTestHelper.assertTermEqualsTermModel(term, result.getTerm());

    }

    @Test
    public void handleRequest_CustomTermDoesNotExists_throwTermNotFoundException() {
        // GIVEN
        String expectedTermId = "H0001";

        DeleteCustomTermRequest request = DeleteCustomTermRequest.builder()
                .withTermId(expectedTermId)
                .build();

        when(termDao.getTerm(request.getTermId())).thenThrow(new TermNotFoundException());

        // WHEN
        // THEN
        assertThrows(TermNotFoundException.class,
                () -> deleteCustomTerm.handleRequest(request, null));

    }
}
