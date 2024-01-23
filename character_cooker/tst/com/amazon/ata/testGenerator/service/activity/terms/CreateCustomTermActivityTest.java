package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.helpers.TermTestHelper;
import com.amazon.ata.testGenerator.service.models.terms.requests.CreateCustomTermRequest;
import com.amazon.ata.testGenerator.service.models.terms.results.CreateCustomTermResult;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateCustomTermActivityTest {
    @Mock
    TermDao termDao;

    CreateCustomTermActivity createCustomTermActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createCustomTermActivity = new CreateCustomTermActivity(termDao);
    }

    /**
     *  Side note for testing
     *  We cannot make an expected Date b/c the Date is generated in the method and not returned
     *  MockStatic is not in the version we are using.
     *  Solutions:
     *  1. We use isA(Term.class) instead of any() to verify a Term object is passed
     *  2. Mockito ArgumentCapture which captures the input Term's field values
     *  3. Update Terms to return the date - Most likely will be done in a later version
     */

    // Success Run Tests:
    @Test
    public void handleRequest_saveCustomTerm_returnTermResult() {
        // Given
        String expectedRomaji = "aka";
        String expectedSymbol = "あか";
        String expectedUsername = "CandyApple";
        String expectTemplateId = "CAt001";
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        CreateCustomTermRequest request = CreateCustomTermRequest.builder()
                .withRomanization(expectedRomaji)
                .withSymbol(expectedSymbol)
                .withUsername(expectedUsername)
                .withTemplateId(expectTemplateId)
                .build();

        when(termDao.isIdUnique(anyString())).thenReturn(true);

        //When
        CreateCustomTermResult result = createCustomTermActivity.handleRequest(request, null);

        // Then
        String expectedTermId = result.getTerm().getTermId();

        verify(termDao).saveTerm(isA(Term.class)); // we don't know the date

        assertEquals(expectedRomaji, result.getTerm().getRomanization());
        assertEquals(expectedSymbol, result.getTerm().getSymbol());
        assertTrue(StringUtils.isAlphanumeric(expectedTermId));
    }

}
