package com.amazon.ata.testGenerator.service.helpers;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.models.TermModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TermTestHelper {

    private static final String[] romanization = new String[] {"a", "i", "u", "e", "o"};
    private static final String[] symbols = new String[] {"あ", "い", "う", "え", "お"};
    private static final String[] ids = new String[] {"000", "001", "002", "003", "004"};

    public static List<Term> generateTermsList (int size, String templateId, String username, String date) {
        List<Term> terms = new ArrayList<>();

        for (int i = 0; i < size && i < ids.length; i++) {
            Term term = new Term();
            term.setTermId(ids[i]);
            term.setRomanization(romanization[i]);
            term.setSymbol(symbols[i]);

            term.setTemplateId(templateId);
            term.setUsername(username);
            term.setDateCreated(date);

            terms.add(term);
        }

        return terms;
    }

    public static void assertTermsEqualTermModels(List<Term> terms, List<TermModel> termModels) {
        assertEquals(terms.size()
                , termModels.size()
                , String.format("Expected terms (%s) and term models (%s) to match)", terms, termModels));

        for (int i = 0; i < terms.size(); i++) {
            assertTermEqualsTermModel(terms.get(i), termModels.get(i),
                    String.format("Expected %dth terms (%s) to match corresponding termModels(%s)",
                            i,
                            terms.get(i),
                            termModels.get(i)));
        }

    }

    public static void assertTermEqualsTermModel(Term term, TermModel termModel) {
        String message = String.format("Expected term %s to match term model %s", term, termModel);
        assertTermEqualsTermModel(term, termModel, message);
    }

    public static void assertTermEqualsTermModel(Term term, TermModel termModel, String message) {
        assertEquals(term.getRomanization(), termModel.getRomanization(), message);
        assertEquals(term.getSymbol(), termModel.getSymbol(), message);
        assertEquals(term.getTermId(), termModel.getTermId(), message);
    }
}
