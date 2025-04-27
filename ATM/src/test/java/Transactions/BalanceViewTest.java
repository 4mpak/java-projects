package Transactions;

import Accounts.Account;
import Accounts.BankException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceViewTest {

    @Test
    void shouldDisplayPositiveBalance() throws BankException {
        Account account = new Account("user", "password");
        account.setBalance(500);

        BalanceView balanceView = new BalanceView();
        balanceView.perform(account);
    }

    @Test
    void shouldThrowExceptionForNegativeBalance() {
        Account account = new Account("user", "password");
        account.setBalance(-100);

        BalanceView balanceView = new BalanceView();

        BankException thrown = assertThrows(BankException.class, () ->
                balanceView.perform(account)
        );

        assertEquals("Balance is negative", thrown.getMessage());
    }
}
