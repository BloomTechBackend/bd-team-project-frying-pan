package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TermDao;
import com.amazon.ata.testGenerator.service.dynamodb.dao.TestTemplateDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Term;
import com.amazon.ata.testGenerator.service.dynamodb.models.TestTemplate;
import com.amazon.ata.testGenerator.service.exceptions.AccountNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.helpers.TemplateTestHelper;
import com.amazon.ata.testGenerator.service.helpers.TermTestHelper;
import com.amazon.ata.testGenerator.service.models.accounts.requests.DeleteAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.DeleteAccountResult;
import com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.amazon.ata.testGenerator.service.helpers.TemplateTestHelper.assertTestTemplatesEqualTemplateModels;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteAccountActivityTest {
    @Mock
    private AccountDao accountDao;

    @Mock
    private TestTemplateDao testTemplateDao;

    @Mock
    private TermDao termDao;

    private DeleteAccountActivity deleteAccountActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        deleteAccountActivity = new DeleteAccountActivity(accountDao, testTemplateDao, termDao);
    }

    // Successful Test Runs
    @Test
    public void requestHandler_deleteAccountOnly_returnsAccount() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);

        List<TestTemplate> expectedTemplates = new ArrayList<>();
        List<Term> expectedTerms = new ArrayList<>();

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);
        when(testTemplateDao.getTemplateByUserTitle(request.getUsername()))
                .thenReturn(expectedTemplates);
        when(termDao.getTermsByUser(request.getUsername())).thenReturn(expectedTerms);
        // WHEN

        DeleteAccountResult result = deleteAccountActivity.handleRequest(request, null);

        // THEN
        verify(accountDao).deleteAccount(expectedAccount);
        verify(testTemplateDao, never()).deleteTemplate(any());
        verify(termDao, never()).deleteTerm(any());

        assertEquals(expectedUsername, result.getUsername());
        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(expectedTemplates, result.getTemplatesDeleted());
        TermTestHelper.assertTermsEqualTermModels(expectedTerms, result.getTermsDeleted());
    }

    // Successful Test Runs
    @Test
    public void requestHandler_deleteAccountWithTemplateAndTerm_returnsAccountWithTemplateAndTerm() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);

        List<TestTemplate> expectedTemplates = TemplateTestHelper.generateTemplates(1, expectedUsername);
        List<Term> expectedTerms = TermTestHelper.generateTermsList(1,
                expectedTemplates.get(0).getTemplateId(),
                expectedUsername,
                TestGeneratorServiceUtils.getDate());

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);
        when(testTemplateDao.getTemplateByUserTitle(request.getUsername()))
                .thenReturn(expectedTemplates);
        when(termDao.getTermsByUser(request.getUsername())).thenReturn(expectedTerms);
        // WHEN

        DeleteAccountResult result = deleteAccountActivity.handleRequest(request, null);

        // THEN
        verify(accountDao).deleteAccount(expectedAccount);
        verify(testTemplateDao).deleteTemplate(expectedTemplates.get(0));
        verify(termDao).deleteTerm(expectedTerms.get(0));

        assertEquals(expectedUsername, result.getUsername());
        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(expectedTemplates, result.getTemplatesDeleted());
        TermTestHelper.assertTermsEqualTermModels(expectedTerms, result.getTermsDeleted());
    }

    // Successful Test Runs
    @Test
    public void requestHandler_deleteAccountWithTemplatesAndTerms_returnsAccountWithTemplatesAndTerms() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);

        List<TestTemplate> expectedTemplates = TemplateTestHelper.generateTemplates(5, expectedUsername);
        List<Term> expectedTerms = TermTestHelper.generateTermsList(5,
                expectedTemplates.get(0).getTemplateId(),
                expectedUsername,
                TestGeneratorServiceUtils.getDate());

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);
        when(testTemplateDao.getTemplateByUserTitle(request.getUsername()))
                .thenReturn(expectedTemplates);
        when(termDao.getTermsByUser(request.getUsername())).thenReturn(expectedTerms);
        // WHEN

        DeleteAccountResult result = deleteAccountActivity.handleRequest(request, null);

        // THEN
        verify(accountDao).deleteAccount(expectedAccount);
        verify(testTemplateDao, atLeast(5)).deleteTemplate(isA(TestTemplate.class));
        verify(termDao, atLeast(5)).deleteTerm(isA(Term.class));

        assertEquals(expectedUsername, result.getUsername());
        TemplateTestHelper.assertTestTemplatesEqualTemplateModels(expectedTemplates, result.getTemplatesDeleted());
        TermTestHelper.assertTermsEqualTermModels(expectedTerms, result.getTermsDeleted());
    }

    // Throw Exceptions Tests

    @Test
    public void requestHandler_invalidUsername_throwsInvalidAttributeValueException() {
        // GIVEN
        String expectedUsername = "expected\\Username";
        String expectedPassword = "expectedPassword125";

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> deleteAccountActivity.handleRequest(request, null));
    }

    @Test
    public void requestHandler_invalidPassword_throwsInvalidAttributeValueException() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expected\\Password";

        List<TestTemplate> expectedTemplates = new ArrayList<>();
        List<Term> expectedTerms = new ArrayList<>();

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> deleteAccountActivity.handleRequest(request, null));
    }

    @Test
    public void requestHandler_accountNotFound_throwsAccountNotFoundException() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenThrow(new AccountNotFoundException());

        // When
        // Then
        assertThrows(AccountNotFoundException.class,
                () -> deleteAccountActivity.handleRequest(request, null));
    }

    @Test
    public void requestHandler_incorrectPassword_throwsInvalidAttributeValueException() {
        // GIVEN
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);

        DeleteAccountRequest request = DeleteAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword("IncorrectPassword")
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> deleteAccountActivity.handleRequest(request, null));
    }
}
