package Accounts;

import java.util.HashMap;

public class CreateClient implements AccountActions
{
    public Account perform(HashMap<String, Account> accounts, String username, String password) throws BankException {
        if (accounts.containsKey(username)) {
            throw new BankException("A client with that name already exists.");
        }
        Account account = new Account(username, password);
        accounts.put(username, account);
        System.out.println("Client " + username + " successfully created");
        return account;
    }
}
