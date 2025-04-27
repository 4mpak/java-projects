package Transactions;

import Accounts.Account;
import Accounts.BankException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefillAccountTest {

    @Test
    void shouldRefillAccountSuccessfully() throws BankException {
        Account account = new Account("user", "password");
        RefillAccount refill = new RefillAccount(300);

        refill.perform(account);

        assertEquals(300, account.getBalance());
    }

    @Test
    void shouldThrowExceptionForZeroRefill() {
        Account account = new Account("user", "password");
        RefillAccount refill = new RefillAccount(0);

        BankException thrown = assertThrows(BankException.class, () ->
                refill.perform(account)
        );

        assertEquals("Amount is zero", thrown.getMessage());
    }
}
