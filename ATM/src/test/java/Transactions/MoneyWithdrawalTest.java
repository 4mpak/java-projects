package Transactions;

import Accounts.Account;
import Accounts.BankException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyWithdrawalTest {

    @Test
    void shouldWithdrawMoneySuccessfully() throws BankException {
        Account account = new Account("user", "password");
        account.setBalance(500);

        MoneyWithdrawal withdrawal = new MoneyWithdrawal(200);
        withdrawal.perform(account);

        assertEquals(300, account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {
        Account account = new Account("user", "password");
        account.setBalance(100);

        MoneyWithdrawal withdrawal = new MoneyWithdrawal(200);

        BankException thrown = assertThrows(BankException.class, () ->
                withdrawal.perform(account)
        );

        assertEquals("There are not enough funds in the account", thrown.getMessage());
    }
}
