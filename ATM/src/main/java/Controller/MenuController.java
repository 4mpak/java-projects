package Controller;

import Accounts.Account;
import Accounts.BankException;

import java.util.HashMap;
import java.util.Scanner;

public class MenuController
{
    private final UserController userController = new UserController();
    private final TransactionController transactionController = new TransactionController();

    public void show() throws BankException {
        HashMap<String, Account> accountsMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            else {
                throw new BankException("Invalid choice");
            }
            switch (choice) {
                case 1:
                    try {
                        transactionController.Run(userController.createAccount(accountsMap));
                    } catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 2:
                    try {
                        transactionController.Run(userController.authenticateAccount(accountsMap));
                    } catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 3);
        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log-in to an existing account");
        System.out.println("3. Exit");
    }
}
