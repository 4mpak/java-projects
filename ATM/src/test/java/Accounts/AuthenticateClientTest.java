package Accounts;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticateClientTest {

    @Test
    void shouldAuthenticateValidUser() throws BankException {
        HashMap<String, Account> accounts = new HashMap<>();
        Account account = new Account("validUser", "password");
        accounts.put("validUser", account);

        AuthenticateClient authenticateClient = new AuthenticateClient();
        Account authenticatedAccount = authenticateClient.perform(accounts, "validUser", "password");

        assertEquals(account, authenticatedAccount);
    }

    @Test
    void shouldThrowExceptionForInvalidUser() {
        HashMap<String, Account> accounts = new HashMap<>();
        AuthenticateClient authenticateClient = new AuthenticateClient();

        BankException thrown = assertThrows(BankException.class, () ->
                authenticateClient.perform(accounts, "invalidUser", "password")
        );

        assertEquals("A client with that username was not found", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionForIncorrectPassword() {
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put("user", new Account("user", "correctPassword"));

        AuthenticateClient authenticateClient = new AuthenticateClient();

        BankException thrown = assertThrows(BankException.class, () ->
                authenticateClient.perform(accounts, "user", "wrongPassword")
        );

        assertEquals("The password is incorrect", thrown.getMessage());
    }
}
