package Transactions;

import Accounts.Account;
import Accounts.BankException;
import lombok.Getter;

@Getter
public class RefillAccount implements TransactionCreate
{
    private final long amount;
    private final String transactionType;

    public RefillAccount(long amount){
        this.amount = amount;
        this.transactionType = "Account refill";
    }

    public String getTransactionType(){ return transactionType; }

    public double getAmount(){
        return amount;
    }

    public void perform(Account account) throws BankException{
        if (amount == 0){
            throw new BankException("Amount is zero");
        }
        account.setBalance(account.getBalance() + amount);
        account.getTransactionList().add(this);
        System.out.println("Refill: +" + amount + " ₽" );
    }
}
