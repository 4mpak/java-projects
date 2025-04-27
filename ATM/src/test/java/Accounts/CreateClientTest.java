package Accounts;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CreateClientTest {

    @Test
    void shouldCreateNewClient() throws BankException {
        HashMap<String, Account> accounts = new HashMap<>();
        CreateClient createClient = new CreateClient();

        Account account = createClient.perform(accounts, "newUser", "password");

        assertNotNull(account);
        assertEquals("newUser", account.getUsername());
        assertTrue(accounts.containsKey("newUser"));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put("existingUser", new Account("existingUser", "password"));

        CreateClient createClient = new CreateClient();

        BankException thrown = assertThrows(BankException.class, () ->
                createClient.perform(accounts, "existingUser", "password")
        );

        assertEquals("A client with that name already exists.", thrown.getMessage());
    }
}
