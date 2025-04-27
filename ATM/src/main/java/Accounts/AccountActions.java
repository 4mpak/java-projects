package Accounts;

import java.util.HashMap;

public interface AccountActions
{
    Account perform(HashMap<String, Account> accounts, String username, String password) throws BankException;
}
