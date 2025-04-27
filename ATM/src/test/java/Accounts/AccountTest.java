package Accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldCreateAccountWithInitialZeroBalance() {
        Account account = new Account("testUser", "testPass");
        assertEquals(0, account.getBalance());
    }
}
