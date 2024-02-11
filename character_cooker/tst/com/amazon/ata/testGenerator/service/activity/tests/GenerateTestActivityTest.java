package com.amazon.ata.testGenerator.service.activity.tests;

import com.amazon.ata.testGenerator.service.activity.tests.GenerateTestActivity;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestRequest;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.*;

public class GenerateTestActivityTest {
    @Mock
    private TermDao termDao;
    private GenerateTestActivity generateTestActivity;

    private final String[] romanization = new String[] {"a", "i", "u", "e", "o"};
    private final String[] symbols = new String[] {"あ", "い", "う", "え", "お"};

    private String[] ids;
    private List<Term> terms;
    private final String expectedQuestions
            = "1.a  _____\n"
            + "2.i  _____\n"
            + "3.u  _____\n"
            + "4.e  _____\n"
            + "5.o  _____\n";

    private final String expectedAnswers
            = "1.a  _____  あ\n"
            + "2.i  _____  い\n"
            + "3.u  _____  う\n"
            + "4.e  _____  え\n"
            + "5.o  _____  お\n";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        generateTestActivity = new GenerateTestActivity(termDao);

        // Given Data
        ids = new String[] {"0", "1", "2", "3", "4"};

        terms = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Term term = new Term();
            term.setTermId(ids[i]);
            term.setRomanization(romanization[i]);
            term.setSymbol(symbols[i]);

            terms.add(term);
        }
    }

    // Success Run Tests:
    @Test
    public void handleRequest_generateTest_returnHiraganaTest() {
        // Givem

        GenerateTestRequest request = GenerateTestRequest.builder()
                .withHiraganaIds(Arrays.asList(ids))
                .withRandomHiragana(false)
                .build();

        when(termDao.getTerm(request.getHiraganaIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getHiraganaIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getHiraganaIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getHiraganaIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getHiraganaIds().get(4))).thenReturn(terms.get(4));

        // When
        GenerateTestResult result = generateTestActivity.handleRequest(request, null);

        // Then
        assertEquals("Test", result.getTitle());
        assertNotNull(result.getHiraganaTest());
        assertNull(result.getKatakanaTest());
        assertNull(result.getCustomTest());

        assertEquals(expectedQuestions, result.getHiraganaTest().getTestQuestions());
        assertEquals(expectedAnswers, result.getHiraganaTest().getTestAnswers());
    }

    @Test
    public void handleRequest_generateTest_returnCustomTest() {
        // Givem
        String customTitle = "Custom Title";
        GenerateTestRequest request = GenerateTestRequest.builder()
                .withTitle(customTitle)
                .withCustomIds(Arrays.asList(ids))
                .withRandomCustom(false)
                .build();

        when(termDao.getTerm(request.getCustomIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getCustomIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getCustomIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getCustomIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getCustomIds().get(4))).thenReturn(terms.get(4));

        // When
        GenerateTestResult result = generateTestActivity.handleRequest(request, null);

        // Then
        assertEquals(customTitle, result.getTitle());
        assertNull(result.getHiraganaTest());
        assertNull(result.getKatakanaTest());
        assertNotNull(result.getCustomTest());

        assertEquals(expectedQuestions, result.getCustomTest().getTestQuestions());
        assertEquals(expectedAnswers, result.getCustomTest().getTestAnswers());
    }


    @Test
    public void handleRequest_generateTest_returnAllTest() {
        // Givem

        String title = "Test: Volume 3";
        GenerateTestRequest request = GenerateTestRequest.builder()
                .withTitle(title)
                .withHiraganaIds(Arrays.asList(ids))
                .withRandomHiragana(false)
                .withKatakanaIds(Arrays.asList(ids))
                .withRandomKatakana(false)
                .withCustomIds(Arrays.asList(ids))
                .withRandomCustom(false)
                .build();

        when(termDao.getTerm(request.getHiraganaIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getHiraganaIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getHiraganaIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getHiraganaIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getHiraganaIds().get(4))).thenReturn(terms.get(4));

        when(termDao.getTerm(request.getKatakanaIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getKatakanaIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getKatakanaIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getKatakanaIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getKatakanaIds().get(4))).thenReturn(terms.get(4));

        when(termDao.getTerm(request.getCustomIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getCustomIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getCustomIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getCustomIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getCustomIds().get(4))).thenReturn(terms.get(4));

        // When
        GenerateTestResult result = generateTestActivity.handleRequest(request, null);

        // Then
        assertEquals(title, result.getTitle());
        assertNotNull(result.getHiraganaTest());
        assertNotNull(result.getKatakanaTest());
        assertNotNull(result.getCustomTest());

        assertEquals(expectedQuestions, result.getHiraganaTest().getTestQuestions());
        assertEquals(expectedAnswers, result.getHiraganaTest().getTestAnswers());

        assertEquals(expectedQuestions, result.getKatakanaTest().getTestQuestions());
        assertEquals(expectedAnswers, result.getKatakanaTest().getTestAnswers());

        assertEquals(expectedQuestions, result.getCustomTest().getTestQuestions());
        assertEquals(expectedAnswers, result.getCustomTest().getTestAnswers());

        // They both have the same inputs
        assertEquals(result.getHiraganaTest().getTestQuestions(), result.getKatakanaTest().getTestQuestions());
        assertEquals(result.getHiraganaTest().getTestAnswers(), result.getKatakanaTest().getTestAnswers());
    }

    // Success Run Tests:
    @Test
    public void handleRequest_generateRandomTest_returnRandomHiraganaTest() {
        // Givem

        GenerateTestRequest request = GenerateTestRequest.builder()
                .withHiraganaIds(Arrays.asList(ids))
                .withRandomHiragana(true)
                .build();

        when(termDao.getTerm(request.getHiraganaIds().get(0))).thenReturn(terms.get(0));
        when(termDao.getTerm(request.getHiraganaIds().get(1))).thenReturn(terms.get(1));
        when(termDao.getTerm(request.getHiraganaIds().get(2))).thenReturn(terms.get(2));
        when(termDao.getTerm(request.getHiraganaIds().get(3))).thenReturn(terms.get(3));
        when(termDao.getTerm(request.getHiraganaIds().get(4))).thenReturn(terms.get(4));

        // When
        GenerateTestResult result = generateTestActivity.handleRequest(request, null);

        // Then
        assertEquals("Test", result.getTitle());
        assertNotNull(result.getHiraganaTest());
        assertNull(result.getKatakanaTest());
        assertNull(result.getCustomTest());

        System.out.println(result.getHiraganaTest().getTitle());
        System.out.println(result.getHiraganaTest().getTestQuestions());
        System.out.println(result.getHiraganaTest().getTestAnswers());
    }
}