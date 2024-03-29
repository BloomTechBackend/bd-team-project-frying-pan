package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.dynamodb.models.Account;
import com.amazon.ata.testGenerator.service.dynamodb.models.Status;
import com.amazon.ata.testGenerator.service.exceptions.AccountNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.LogOutAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.requests.LoginAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.LogOutAccountResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogOutAccountActivityTest {
    @Mock
    private AccountDao accountDao;

    private LogOutAccountActivity logOutAccountActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        logOutAccountActivity = new LogOutAccountActivity(accountDao);
    }

    // Success Run Tests:
    @Test
    public void handleRequest_AccountLoggedIn_returnsUserLoggedOut() {
        // Given
        String expectedUsername = "expectedUsername";
        String expectedPassword = "expectedPassword125";
        String expectedStatus = Status.LOGGED_OUT.toString();

        Account expectedAccount = new Account();
        expectedAccount.setUsername(expectedUsername);
        expectedAccount.setPassword(expectedPassword);
        expectedAccount.setStatus(Status.LOGGED_IN.toString());

        LogOutAccountRequest request = LogOutAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(accountDao.getAccount(expectedUsername)).thenReturn(expectedAccount);

        // When
        LogOutAccountResult result = logOutAccountActivity.handleRequest(request, null);

        // Then
        verify(accountDao).getAccount(expectedUsername);

        assertEquals(Status.LOGGED_OUT.toString(), expectedAccount.getStatus());
        verify(accountDao).saveAccount(expectedAccount);

        assertEquals(expectedUsername, result.getUsername());
        assertEquals(expectedStatus, result.getStatus());
    }

    // Throw Exceptions tests
    @Test
    public void handleRequest_invalidUsername_throwsInvalidAttributeValueException() {
        // Given
        String expectedUsername = "expected\\Username";

        LogOutAccountRequest request = LogOutAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> logOutAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_userDoesNotExist_AccountNotFoundException() {
        // Given
        String expectedUsername = "expectedUsername";

        LogOutAccountRequest request = LogOutAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        // Username is not found
        when(accountDao.getAccount(expectedUsername)).thenThrow(new AccountNotFoundException());
        // When
        // Then
        assertThrows(AccountNotFoundException.class,
                () -> logOutAccountActivity.handleRequest(request, null));
    }


}
