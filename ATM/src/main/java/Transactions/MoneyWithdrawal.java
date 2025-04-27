package Transactions;

import Accounts.Account;
import Accounts.BankException;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class MoneyWithdrawal implements TransactionCreate
{
    private final double amount;
    private final String transactionType;

    public MoneyWithdrawal (double amount) {
        this.amount = amount;
        this.transactionType = "Withdrawing money";
    }

    public String getTransactionType(){
        return transactionType;
    }

    public double getAmount(){
        return amount;
    }

    public void perform(Account account) throws BankException {
        if (account.getBalance() < amount) {
            throw new BankException("There are not enough funds in the account");
        }
        account.setBalance(account.getBalance() - amount);
        account.getTransactionList().add(this);
        System.out.println("Withdrawal: -" + amount + " ₽" );
    }
}
