package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Status;
import com.amazon.ata.testGenerator.service.exceptions.AccountNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.LoginAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.LoginAccountResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginAccountActivityTest {
    @Mock
    private AccountDao accountDao;

    private LoginAccountActivity loginAccountActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        loginAccountActivity = new LoginAccountActivity(accountDao);
    }

    // Success Run Tests:
    @Test
    public void handleRequest_accountLoggedOut_returnsUserLoggedIn() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";
        String expectedStatus = Status.LOGGED_IN.toString();

        Account retrievedAccount = new Account();
        retrievedAccount.setUsername(expectedUsername);
        retrievedAccount.setPassword(expectedPassword);
        retrievedAccount.setStatus(Status.LOGGED_OUT.toString());

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);
        expectedAccount.setStatus(expectedStatus);

        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // Username is found
        when(accountDao.getAccount(expectedUsername)).thenReturn(retrievedAccount);
        // When
        LoginAccountResult result = loginAccountActivity.handleRequest(request, null);

        // Then
        verify(accountDao).getAccount(expectedUsername);
        verify(accountDao).saveAccount(expectedAccount);

        assertEquals(expectedUsername, result.getUsername());
        assertEquals(expectedStatus, result.getStatus());
    }

    @Test
    public void handleRequest_accountLoggedIn_returnsUserLoggedIn() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";
        String expectedStatus = Status.LOGGED_IN.toString();

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);
        expectedAccount.setStatus(expectedStatus);

        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // Username is found
        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);
        // When
        LoginAccountResult result = loginAccountActivity.handleRequest(request, null);

        // Then
        verify(accountDao).getAccount(expectedUsername);
        verify(accountDao).saveAccount(expectedAccount);

        assertEquals(expectedUsername, result.getUsername());
        assertEquals(expectedStatus, result.getStatus());

    }

    // Throw Exceptions tests
    @Test
    public void handleRequest_loginAccount_InvalidUsername() {
        // Given
        String expectedUsername = "expected\\Username";
        String expectedPassword = "expectedPassword125";

        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> loginAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_loginAccount_InvalidPassword() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "cat";

        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> loginAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_loginAccount_AccountNotFoundException() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";

        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(expectedPassword)
                .build();

        // Username is not found
        when(accountDao.getAccount(expectedUsername)).thenThrow(new AccountNotFoundException());
        // When
        // Then
        assertThrows(AccountNotFoundException.class,
                () -> loginAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_loginAccount_PasswordNotConfirmed() {
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";
        String incorrectPassword = "incorrectPassword";

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);

        // Loaded with incorrect password
        LoginAccountRequest request = LoginAccountRequest.builder()
                .withUsername(expectedUsername)
                .withPassword(incorrectPassword)
                .build();

        // Username is found
        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);

        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> loginAccountActivity.handleRequest(request, null));
    }
}
