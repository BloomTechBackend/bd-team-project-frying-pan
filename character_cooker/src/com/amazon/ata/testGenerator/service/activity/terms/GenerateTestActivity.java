package com.amazon.ata.testGenerator.service.activity.terms;

import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TestModel;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestRequest;
import com.amazon.ata.testGenerator.service.models.tests.GenerateTestResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class GenerateTestActivity implements RequestHandler<GenerateTestRequest, GenerateTestResult> {
    private final Logger log = LogManager.getLogger();
    private final TermDao termDao;

    @Inject
    public GenerateTestActivity(TermDao termDao) {
        this.termDao = termDao;
    }


    @Override
    public GenerateTestResult handleRequest(GenerateTestRequest request, Context context) {

        String title = "Test";
        if (!request.getTitle().isEmpty()) {
            title = request.getTitle();
        }

        // Call CreateTestModel 3 times
        TestModel hiragana = createTestModel(request.getHiraganaIds(), request.isRandomHiragana(), "Hiragana");
        TestModel katakana = createTestModel(request.getKatakanaIds(), request.isRandomKatakana(), "Katakana");
        TestModel custom = createTestModel(request.getCustomIds(), request.isRandomCustom(), "Hiragana");

        return GenerateTestResult.builder()
                .withTitle(title)
                .withHiraganaTest(hiragana)
                .withKatakanaTest(katakana)
                .withCustomTest(custom)
                .build();
    }

    /**
     * Helper Function to create the TestModel for each variation
     * Retrieves the Terms, generates the questions and answers text and bundles it up in a Test model
     *
     * @param ids list of test ids to generate
     * @param isRandom optional parameter the user selects for the characters order
     * @return
     */
    private TestModel createTestModel(List<String> ids, boolean isRandom, String title) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }

        //
        if (isRandom) {
            Collections.shuffle(ids);
        }

        StringBuilder questions = new StringBuilder();
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            Term term = termDao.getTerm(ids.get(i));
            questions.append(String.format("%d.%s  _____\n", i + 1, term.getRomanization()));
            answers.append(String.format("%d.%s  %s\n", i + 1, term.getRomanization(), term.getSymbol()));
        }

        return TestModel.builder()
                .withTestQuestions(questions.toString())
                .withTestAnswers(answers.toString())
                .withTitle(title)
                .build();
    }
}
