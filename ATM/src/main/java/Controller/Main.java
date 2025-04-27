package Controller;

import Accounts.BankException;

public class Main
{
    public static void main(String[] args) throws BankException {
        MenuController menuController = new MenuController();
        try {
            menuController.show();
        }
        catch (BankException error) {
            System.out.println ("Error: " + error.getMessage());
        }
    }
}
