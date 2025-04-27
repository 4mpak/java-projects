package Accounts;

import java.util.HashMap;

public class AuthenticateClient implements AccountActions
{
    public Account perform(HashMap<String, Account> accounts, String username, String password) throws BankException {
        if (!accounts.containsKey(username)) {
            throw new BankException("A client with that username was not found");
        }

        if (!accounts.get(username).getPassword().equals(password)) {
            throw new BankException("The password is incorrect");
        }

        if (!accounts.get(username).getPassword().equals(password)) {

        }

        System.out.println("Client " + username + " successfully authenticated");
        return accounts.get(username);
    }
}
