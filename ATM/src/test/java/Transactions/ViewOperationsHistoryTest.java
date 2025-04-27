package Transactions;

import Accounts.Account;
import Accounts.BankException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewOperationsHistoryTest {

    @Test
    void shouldThrowExceptionWhenNoTransactions() {
        Account account = new Account("user", "password");

        ViewOperationsHistory viewOperationsHistory = new ViewOperationsHistory();

        BankException thrown = assertThrows(BankException.class, () ->
                viewOperationsHistory.perform(account)
        );

        assertEquals("There is no transaction to perform", thrown.getMessage());
    }

    @Test
    void shouldDisplayOperationsHistory() throws BankException {
        Account account = new Account("user", "password");
        account.setBalance(1000);

        RefillAccount refill = new RefillAccount(500);
        refill.perform(account);

        MoneyWithdrawal withdrawal = new MoneyWithdrawal(300);
        withdrawal.perform(account);

        ViewOperationsHistory history = new ViewOperationsHistory();
        history.perform(account);
    }
}
