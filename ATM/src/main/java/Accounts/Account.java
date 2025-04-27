package Accounts;

import Transactions.TransactionCreate;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class Account{
    private final String password;
    private final String username;
    @Setter
    private double balance;
    private ArrayList<TransactionCreate> transactionList;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactionList = new ArrayList<>();
    }
}
