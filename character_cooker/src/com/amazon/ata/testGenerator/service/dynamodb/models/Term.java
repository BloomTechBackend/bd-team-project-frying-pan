package com.amazon.ata.testGenerator.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Objects;

@DynamoDBTable(tableName = "LBC_Terms")
public class Term {
    public static final String USERNAME_DATE_INDEX = "UserDateIndex";
    public static final String TEMPLATE_DATE_INDEX = "templateIdDateIndex";

    // Included in Hiragana and Katakana
    private String termId;
    private String romanization;
    private String symbol;

    // Included in Custom Terms
    private String templateId;
    private String dateCreated;
    private String definition;
    private String username;
    private boolean added;

    public Term() {}

    @DynamoDBHashKey(attributeName = "termId")
    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @DynamoDBAttribute(attributeName = "romanization")
    public String getRomanization() {
        return romanization;
    }

    public void setRomanization(String romanization) {
        this.romanization = romanization;
    }

    @DynamoDBAttribute(attributeName = "symbol")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = TEMPLATE_DATE_INDEX, attributeName = "templateId")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String definition) {
        this.templateId = definition;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {TEMPLATE_DATE_INDEX, USERNAME_DATE_INDEX}
            , attributeName = "dateCreated")
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @DynamoDBAttribute(attributeName = "definition")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = USERNAME_DATE_INDEX, attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "added")
    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return added == term.added
                && Objects.equals(termId, term.termId)
                && Objects.equals(romanization, term.romanization)
                && Objects.equals(symbol, term.symbol)
                && Objects.equals(templateId, term.templateId)
                && Objects.equals(dateCreated, term.dateCreated)
                && Objects.equals(definition, term.definition)
                && Objects.equals(username, term.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termId, romanization, symbol, templateId, dateCreated, definition, username, added);
    }
}
