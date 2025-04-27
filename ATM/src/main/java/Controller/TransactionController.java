package Controller;

import Accounts.Account;
import Accounts.BankException;
import Transactions.*;

import java.util.Scanner;

public class TransactionController
{
    public void Run(Account account) throws BankException {
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            if (in.hasNextInt()) {
                choice = in.nextInt();
            }
            else {
                throw new BankException("Invalid input");
            }
            long amount;
            switch (choice) {
                case 1:
                    System.out.println("Enter the amount: ");
                    amount = in.nextLong();
                    TransactionCreate refill = new RefillAccount(amount);
                    try {
                        refill.perform(account);
                    }
                    catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter the amount: ");
                    amount = in.nextLong();
                    TransactionCreate withdrawing = new MoneyWithdrawal(amount);
                    try {
                        withdrawing.perform(account);
                    }
                    catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 3:
                    TransactionCreate operationsHistory = new ViewOperationsHistory();
                    try {
                        operationsHistory.perform(account);
                    }
                    catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 4:
                    TransactionCreate balance = new BalanceView();
                    try {
                        balance.perform(account);
                    }
                    catch (BankException error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);
    }

    public static void displayMenu() {
        System.out.println("1. Refill");
        System.out.println("2. Withdrawing");
        System.out.println("3. Operations history");
        System.out.println("4. Balance");
        System.out.println("5. Exit");
    }
}
