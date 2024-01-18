package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.CreateAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.CreateAccountResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateAccountActivityTest {
    @Mock
    private AccountDao accountDao;

    private CreateAccountActivity createAccountActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createAccountActivity = new CreateAccountActivity(accountDao);
    }

    // Success Run Tests:
    @Test
    public void handleRequest_createAccount_returnsUsername() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account account = new Account();
        account.setUsername(expectedUsername);
        account.setPassword(expectedPassword);

        CreateAccountRequest request = CreateAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .withPasswordConfirm(expectedPassword)
                .build();



        // When
        CreateAccountResult result = createAccountActivity.handleRequest(request, null);

        // Then
        verify(accountDao).saveAccount(account);

        assertEquals(expectedUsername, result.getUsername());
    }

    // Throw Exceptions tests
    @Test
    public void handleRequest_createAccount_InvalidUsername() {
        // Given
        String expectedUsername = "expected Username";
        String expectedPassword = "expectedPassword125";

        CreateAccountRequest request = CreateAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .withPasswordConfirm(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> createAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_createAccount_InvalidPassword() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "cat";

        CreateAccountRequest request = CreateAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .withPasswordConfirm(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> createAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_createAccount_UsernameAlreadyExists() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        Account account = new Account();
        account.setUsername(expectedUsername);
        account.setPassword(expectedPassword);

        CreateAccountRequest request = CreateAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .withPasswordConfirm(expectedPassword)
                .build();

        // Username is taken already
        when(accountDao.getAccount(expectedUsername)).thenReturn(account);
        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> createAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_createAccount_PasswordNotConfirmed() {
        // Given
        String expectedUsername = "expected Username";
        String expectedPassword = "expectedPassword125";
        String wrongPassword = "cat";

        CreateAccountRequest request = CreateAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .withPasswordConfirm(wrongPassword)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(null);
        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> createAccountActivity.handleRequest(request, null));
    }
}
