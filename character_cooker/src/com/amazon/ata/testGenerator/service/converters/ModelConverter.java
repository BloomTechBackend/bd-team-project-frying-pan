package com.amazon.ata.testGenerator.service.converters;

import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.models.TemplateModel;
import com.amazon.ata.testGenerator.service.models.TermModel;

public class ModelConverter {

    public static TemplateModel toTemplateModel(TestTemplate template) {
        return TemplateModel.builder()
                .withTemplateId(template.getTemplateId())
                .withUsername(template.getUsername())
                .withHiraganaIdList(template.getHiraganaIdList())
                .withKatakanaIdList(template.getKatakanaIdList())
                .withDateModified(template.getDateModified())
                .build();
    }

    public static TermModel toTermModel (Term term) {
        return TermModel.builder()
                .withTermId(term.getTermId())
                .withRomanization(term.getRomanization())
                .withSymbol(term.getSymbol())
                .build();
    }

}
