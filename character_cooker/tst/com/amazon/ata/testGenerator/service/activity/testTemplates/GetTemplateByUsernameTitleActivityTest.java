package com.amazon.ata.testGenerator.service.activity.testTemplates;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.helpers.TemplateTestHelper;
import com.amazon.ata.testGenerator.service.models.testTemplates.requests.GetTemplateByUsernameTitleRequest;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTemplateByUsernameTitleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetTemplateByUsernameTitleActivityTest {
    @Mock
    TestTemplateDao testTemplateDao;

    GetTemplateByUsernameTitleActivity getTemplateByUsernameTitleActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getTemplateByUsernameTitleActivity = new GetTemplateByUsernameTitleActivity(testTemplateDao);
    }

    @Test
    public void handlerRequest_getTestTemplates_returnsTemplateModels() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        List<TestTemplate> templates = TemplateTestHelper.generateTemplates(5, expectedUsername);

        GetTemplateByUsernameTitleRequest request = GetTemplateByUsernameTitleRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(testTemplateDao.getTemplateByUserTitle(expectedUsername)).thenReturn(templates);

        // WHEN
        GetTemplateByUsernameTitleResult result = getTemplateByUsernameTitleActivity.handleRequest(request, null);
        // THEN

        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(templates, result.getTemplates());
    }

    @Test
    public void handlerRequest_getTestTemplate_returnsTemplateModel() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        List<TestTemplate> templates = TemplateTestHelper.generateTemplates(1, expectedUsername);

        GetTemplateByUsernameTitleRequest request = GetTemplateByUsernameTitleRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(testTemplateDao.getTemplateByUserTitle(expectedUsername)).thenReturn(templates);

        // WHEN
        GetTemplateByUsernameTitleResult result = getTemplateByUsernameTitleActivity.handleRequest(request, null);
        // THEN

        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(templates, result.getTemplates());
    }

    @Test
    public void handlerRequest_getZeroTemplates_returnsZeroModels() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        List<TestTemplate> templates = TemplateTestHelper.generateTemplates(0, expectedUsername);

        GetTemplateByUsernameTitleRequest request = GetTemplateByUsernameTitleRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(testTemplateDao.getTemplateByUserTitle(expectedUsername)).thenReturn(templates);

        // WHEN
        GetTemplateByUsernameTitleResult result = getTemplateByUsernameTitleActivity.handleRequest(request, null);
        // THEN

        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(templates, result.getTemplates());
    }
}
