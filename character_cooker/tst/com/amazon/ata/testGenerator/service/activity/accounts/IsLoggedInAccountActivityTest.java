package com.amazon.ata.testGenerator.service.activity.accounts;

import com.amazon.ata.testGenerator.service.dynamodb.dao.AccountDao;
import com.amazon.ata.testGenerator.service.exceptions.AccountNotFoundException;
import com.amazon.ata.testGenerator.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.testGenerator.service.exceptions.UnauthorizedAccessException;
import com.amazon.ata.testGenerator.service.models.accounts.requests.IsLoggedInAccountRequest;
import com.amazon.ata.testGenerator.service.models.accounts.results.IsLoggedInAccountResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class IsLoggedInAccountActivityTest {
    @Mock
    private AccountDao accountDao;

    IsLoggedInAccountActivity isLoggedInAccountActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        isLoggedInAccountActivity = new IsLoggedInAccountActivity(accountDao);
    }

    // Success Run Tests:
    @Test
    public void handleRequest_accountIsLoggedIn_returnsLoggedIn() {
        // Given
        String expectedUsername = "expectedUsername";

        IsLoggedInAccountRequest request = IsLoggedInAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(accountDao.isLoggedIn(expectedUsername)).thenReturn(true);
        // When
        IsLoggedInAccountResult result = isLoggedInAccountActivity.handleRequest(request, null);

        // Then
        verify(accountDao).isLoggedIn(expectedUsername);

        assertEquals(expectedUsername, result.getUsername());
        assertTrue(result.isLoggedIn());
    }


    // Throw Exceptions tests
    @Test
    public void handleRequest_invalidUsername_throwsInvalidAttributeValueException() {
        // Given
        String expectedUsername = "expected Username";

        IsLoggedInAccountRequest request = IsLoggedInAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        // When
        // Then
        assertThrows(InvalidAttributeValueException.class,
                () -> isLoggedInAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_userDoesNotExist_throwsAccountNotFoundException() {
        // Given
        String expectedUsername = "expectedUsername";

        IsLoggedInAccountRequest request = IsLoggedInAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(accountDao.isLoggedIn(expectedUsername)).thenThrow(new AccountNotFoundException());
        // When
        // Then
        assertThrows(AccountNotFoundException.class,
                () -> isLoggedInAccountActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_accountIsLoggedOut_throwsUnauthorizedAccessException() {
        // Given
        String expectedUsername = "expectedUsername";

        IsLoggedInAccountRequest request = IsLoggedInAccountRequest.builder()
                .withUsername(expectedUsername)
                .build();

        when(accountDao.isLoggedIn(expectedUsername)).thenThrow(new UnauthorizedAccessException());
        // When
        // Then
        assertThrows(UnauthorizedAccessException.class,
                () -> isLoggedInAccountActivity.handleRequest(request, null));
    }
}
