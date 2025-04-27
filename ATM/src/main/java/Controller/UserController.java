package Controller;

import Accounts.*;

import java.util.HashMap;
import java.util.Scanner;

public class UserController
{
    String username, password;

    public void run(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = in.nextLine();
        System.out.print("Enter password: ");
        password = in.nextLine();
    }

    public Account createAccount(HashMap<String, Account> accountsMap) throws BankException {
        run();
        AccountActions createAcc = new CreateClient();
        return createAcc.perform(accountsMap, username, password);
    }

    public Account authenticateAccount(HashMap<String, Account> accountsMap) throws BankException {
        run();
        AccountActions createAcc = new AuthenticateClient();
        return createAcc.perform(accountsMap, username, password);
    }
}
