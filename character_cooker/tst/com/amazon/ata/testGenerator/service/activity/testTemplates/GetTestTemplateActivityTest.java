package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.converters.ModelConverter;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.exceptions.TestTemplateNotFoundException;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.GetTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.UpdateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTestTemplateResult;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.UpdateTestTemplateResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetTestTemplateActivityTest {
    @Mock
    TestTemplateDao testTemplateDao;

    GetTestTemplateActivity getTestTemplateActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getTestTemplateActivity = new GetTestTemplateActivity(testTemplateDao);
    }

    @Test
    public void handlerRequest_getTestTemplate_returnsTemplateModel() {
        // GIVEN
        String expectedTemplateId = "ID0001";
        String expectedTitle = "expectedTitle";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = Arrays.asList(new String[]{"H000", "H001", "H002", "H003", "H004"});
        List<String> expectedIds2 = Arrays.asList(new String[] {"K000", "K001", "K002","K003","K004"});
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        TestTemplate expectedTemplate = new TestTemplate();
        expectedTemplate.setTemplateId(expectedTemplateId);
        expectedTemplate.setTitle(expectedTitle);
        expectedTemplate.setHiraganaIdList(expectedIds1);
        expectedTemplate.setKatakanaIdList(expectedIds2);
        expectedTemplate.setUsername(expectedUsername);
        expectedTemplate.setDateModified(expectedDate);

        GetTestTemplateRequest request = GetTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenReturn(expectedTemplate);

        // WHEN
        GetTestTemplateResult result = getTestTemplateActivity.handleRequest(request, null);
        // THEN

        assertEquals(expectedTemplateId, result.getTemplate().getTemplateId());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());
        assertEquals(expectedDate, result.getTemplate().getDateModified());
    }

    @Test
    public void handlerRequest_getTemplateWithEmptyTerms_returnsTemplateModel() {
        // GIVEN
        String expectedTemplateId = "ID0001";
        String expectedTitle = "expectedTitle";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = new ArrayList<>();
        List<String> expectedIds2 = new ArrayList<>();
        String expectedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        TestTemplate expectedTemplate = new TestTemplate();
        expectedTemplate.setTemplateId(expectedTemplateId);
        expectedTemplate.setTitle(expectedTitle);
        expectedTemplate.setHiraganaIdList(expectedIds1);
        expectedTemplate.setKatakanaIdList(expectedIds2);
        expectedTemplate.setUsername(expectedUsername);
        expectedTemplate.setDateModified(expectedDate);

        GetTestTemplateRequest request = GetTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenReturn(expectedTemplate);

        // WHEN
        GetTestTemplateResult result = getTestTemplateActivity.handleRequest(request, null);
        // THEN

        assertEquals(expectedTemplateId, result.getTemplate().getTemplateId());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());
        assertEquals(expectedDate, result.getTemplate().getDateModified());
    }

    @Test
    public void handlerRequest_templateDoesNotExist_throwsTestTemplateNotFoundException() {
        // GIVEN
        String expectedTemplateId = "ID0001";

        GetTestTemplateRequest request = GetTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenThrow(new TestTemplateNotFoundException());

        // WHEN
        // THEN
        assertThrows(TestTemplateNotFoundException.class,
                () -> getTestTemplateActivity.handleRequest(request, null));

    }
}
