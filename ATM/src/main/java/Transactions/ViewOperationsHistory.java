package Transactions;

import Accounts.Account;
import Accounts.BankException;

public class ViewOperationsHistory implements TransactionCreate
{
    public String getTransactionType(){
        return "View Operations History";
    }

    public double getAmount(){
        return 0;
    }

    public void perform(Account account) throws BankException{
        if (account.getTransactionList().isEmpty()){
            throw new BankException("There is no transaction to perform");
        }

        for (int i = 0; i < account.getTransactionList().size(); i++){
            if (account.getTransactionList().get(i).getTransactionType().equals("Withdrawing money")){
                System.out.println("Withdrawal of money from the account: -" + account.getTransactionList().get(i).getAmount() + " ₽");
            }
            if (account.getTransactionList().get(i).getTransactionType().equals("Account refill")){
                System.out.println("Account refill: +" + account.getTransactionList().get(i).getAmount() + " ₽");
            }
        }
    }
}
