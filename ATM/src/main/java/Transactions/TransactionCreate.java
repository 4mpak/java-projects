package Transactions;

import Accounts.Account;
import Accounts.BankException;

import java.math.BigDecimal;

public interface TransactionCreate
{
    String getTransactionType();
    double getAmount();
    void perform(Account account) throws BankException;
}
