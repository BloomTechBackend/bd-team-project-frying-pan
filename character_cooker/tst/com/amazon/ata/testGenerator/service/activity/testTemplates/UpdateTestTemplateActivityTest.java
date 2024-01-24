package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.exceptions.TermNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.TestTemplateNotFoundException;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.CreateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.UpdateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.UpdateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdateTestTemplateActivityTest {
    @Mock
    private TestTemplateDao testTemplateDao;

    private UpdateTestTemplateActivity updateTestTemplateActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updateTestTemplateActivity = new UpdateTestTemplateActivity(testTemplateDao);
    }

    // Success Run Tests:
    @Test
    public void handlerRequest_updateTitleAndTermLists_returnsTemplateModel() {
        // GIVEN
        String expectedTemplateId = "ID0001";
        String expectedTitle = "title";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"});
        List<String> expectedIds2 = Arrays.asList(new String[] {"K000", "K001", "K002","K003","K004"});
        String startingDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        TestTemplate startingTemplate = new TestTemplate();
        startingTemplate.setTemplateId(expectedTemplateId);
        startingTemplate.setTitle("old name");
        startingTemplate.setHiraganaIdList(new ArrayList<>());
        startingTemplate.setKatakanaIdList(new ArrayList<>());
        startingTemplate.setUsername(expectedUsername);
        startingTemplate.setDateModified(startingDate);

        UpdateTestTemplateRequest request = UpdateTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .withTitle(expectedTitle)
                .withUsername(expectedUsername)
                .withHiraganaIdList(expectedIds1)
                .withKatakanaIdList(expectedIds2)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenReturn(startingTemplate);
        when(testTemplateDao.saveTemplate(startingTemplate)).thenReturn(startingTemplate);

        // WHEN
        UpdateTestTemplateResult result = updateTestTemplateActivity.handleRequest(request, null);
        // THEN

        verify(testTemplateDao).saveTemplate(startingTemplate);

        assertEquals(expectedTemplateId, result.getTemplate().getTemplateId());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());

        // Verify the new date is more recent than the old date: starting date < new date modified
        assertTrue(startingDate.compareTo(result.getTemplate().getDateModified()) < 0);
    }

    @Test
    public void handlerRequest_updateExistingList_returnsTemplateModel() {
        // GIVEN
        String expectedTemplateId = "ID0001";
        String expectedTitle = "title";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"});
        List<String> expectedIds2 = Arrays.asList(new String[] {"K000", "K001"});
        String startingDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        TestTemplate startingTemplate = new TestTemplate();
        startingTemplate.setTemplateId(expectedTemplateId);
        startingTemplate.setTitle("old name");
        startingTemplate.setHiraganaIdList(Arrays.asList(new String[] {"H000", "H001"}));
        startingTemplate.setKatakanaIdList(Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"}));
        startingTemplate.setUsername(expectedUsername);
        startingTemplate.setDateModified(startingDate);

        UpdateTestTemplateRequest request = UpdateTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .withTitle(expectedTitle)
                .withUsername(expectedUsername)
                .withHiraganaIdList(expectedIds1)
                .withKatakanaIdList(expectedIds2)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenReturn(startingTemplate);
        when(testTemplateDao.saveTemplate(startingTemplate)).thenReturn(startingTemplate);

        // WHEN
        UpdateTestTemplateResult result = updateTestTemplateActivity.handleRequest(request, null);
        // THEN

        verify(testTemplateDao).saveTemplate(startingTemplate);

        assertEquals(expectedTemplateId, result.getTemplate().getTemplateId());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());

        // Verify the new date is more recent than the old date: starting date < new date modified
        assertTrue(startingDate.compareTo(result.getTemplate().getDateModified()) < 0);
    }

    // Throw Exception Tests:
    @Test
    public void handlerRequest_templeDoesNotExist_throwsTestTemplateNotFoundException() {
        // GIVEN
        String expectedTemplateId = "ID0001";
        String expectedTitle = "title";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"});
        List<String> expectedIds2 = Arrays.asList(new String[] {"K000", "K001"});
        String startingDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        TestTemplate startingTemplate = new TestTemplate();
        startingTemplate.setTemplateId(expectedTemplateId);
        startingTemplate.setTitle("old name");
        startingTemplate.setHiraganaIdList(Arrays.asList(new String[] {"H000", "H001"}));
        startingTemplate.setKatakanaIdList(Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"}));
        startingTemplate.setUsername(expectedUsername);
        startingTemplate.setDateModified(startingDate);

        UpdateTestTemplateRequest request = UpdateTestTemplateRequest.builder()
                .withTemplateId(expectedTemplateId)
                .withTitle(expectedTitle)
                .withUsername(expectedUsername)
                .withHiraganaIdList(expectedIds1)
                .withKatakanaIdList(expectedIds2)
                .build();

        when(testTemplateDao.getTemplate(expectedTemplateId)).thenThrow(new TestTemplateNotFoundException());

        // WHEN
        assertThrows(TestTemplateNotFoundException.class,
                () -> updateTestTemplateActivity.handleRequest(request, null));
    }


}
