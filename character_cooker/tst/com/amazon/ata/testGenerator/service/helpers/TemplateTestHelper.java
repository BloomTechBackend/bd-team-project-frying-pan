package com.amazon.ata.testGenerator.service.helpers;

import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateTestHelper {

    public static List<TestTemplate> generateTemplates (int size, String username) {
        List<TestTemplate> templates = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TestTemplate template = generateTemplate(i);
            template.setUsername(username);
            templates.add(template);
        }

        return templates;
    }

    //
    public static TestTemplate generateTemplate(int sequenceNumber) {
        TestTemplate template = new TestTemplate();
        template.setTemplateId("ID000" + sequenceNumber);
        template.setTitle("title" + sequenceNumber);
        template.setDateModified(TestGeneratorServiceUtils.getDate());

        return template;
    }

    // Assertion Helpers
    public static void assertTestTemplatesEqualTemplateModels(List<TestTemplate> expected, List<TemplateModel> result) {
        for (int i = 0; i < expected.size(); i++) {
            assertTestTemplateEqualTemplateModel(expected.get(i), result.get(i));
        }
    }

    public static void assertTestTemplateEqualTemplateModel(TestTemplate expected, TemplateModel result) {
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getUsername(), result.getUsername());
        assertEquals(expected.getTemplateId(), result.getTemplateId());
        assertEquals(expected.getHiraganaIdList(), result.getHiraganaIdList());
        assertEquals(expected.getKatakanaIdList(), result.getKatakanaIdList());
        assertEquals(expected.getDateModified(), result.getDateModified());
    }

}
