package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.CreateTestTemplateRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.CreateTestTemplateResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateTestTemplateActivityTest {
    @Mock
    private TestTemplateDao testTemplateDao;

    private CreateTestTemplateActivity createTestTemplateActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createTestTemplateActivity = new CreateTestTemplateActivity(testTemplateDao);
    }

    // Success Run Tests:

    @Test
    public void handlerRequest_saveWithTermList_returnsTemplateModel() {
        // GIVEN
        String expectedTitle = "title";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = Arrays.asList(new String[] {"H000", "H001", "H002","H003","H004"});
        List<String> expectedIds2 = Arrays.asList(new String[] {"K000", "K001", "K002","K003","K004"});

        CreateTestTemplateRequest request = CreateTestTemplateRequest.builder()
                .withTitle(expectedTitle)
                .withUsername(expectedUsername)
                .withHiraganaIdList(expectedIds1)
                .withKatakanaIdList(expectedIds2)
                .build();

        when(testTemplateDao.isIdUnique(anyString())).thenReturn(true);

        // WHEN
        CreateTestTemplateResult result = createTestTemplateActivity.handleRequest(request, null);
        // THEN

        verify(testTemplateDao).saveTemplate(isA(TestTemplate.class));

        assertEquals(TestGeneratorServiceUtils.TEMPLATE_ID_LENGTH, result.getTemplate().getTemplateId().length());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());
    }

    @Test
    public void handlerRequest_saveWithEmptyTermList_returnsTemplateModel() {
        // GIVEN
        String expectedTitle = "title";
        String expectedUsername = "expectedUsername";
        List<String> expectedIds1 = new ArrayList<>();
        List<String> expectedIds2 = new ArrayList<>();

        CreateTestTemplateRequest request = CreateTestTemplateRequest.builder()
                .withTitle(expectedTitle)
                .withUsername(expectedUsername)
                .withHiraganaIdList(expectedIds1)
                .withKatakanaIdList(expectedIds2)
                .build();

        when(testTemplateDao.isIdUnique(anyString())).thenReturn(true);

        // WHEN
        CreateTestTemplateResult result = createTestTemplateActivity.handleRequest(request, null);
        // THEN

        verify(testTemplateDao).saveTemplate(isA(TestTemplate.class));

        assertEquals(TestGeneratorServiceUtils.TEMPLATE_ID_LENGTH, result.getTemplate().getTemplateId().length());
        assertEquals(expectedTitle, result.getTemplate().getTitle());
        assertEquals(expectedUsername, result.getTemplate().getUsername());
        assertEquals(expectedIds1, result.getTemplate().getHiraganaIdList());
        assertEquals(expectedIds2, result.getTemplate().getKatakanaIdList());
    }


}
