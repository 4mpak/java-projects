package Transactions;

import Accounts.Account;
import Accounts.BankException;

public class BalanceView implements TransactionCreate
{
    public String getTransactionType(){
        return "Balance";
    }
    public double getAmount(){
        return 0;
    }
    public void perform(Account account) throws BankException{
        if (account.getBalance() < 0){
            throw new BankException("Balance is negative");
        }
        System.out.println("Current balance: " + account.getBalance() + " ₽");
    }
}
