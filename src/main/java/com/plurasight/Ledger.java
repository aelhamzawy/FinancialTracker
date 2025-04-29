package com.plurasight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showLedgerMenu() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    showAllTransactions();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
//                case "R":
//                    ReportMenu.showReportMenu();
//                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Ledger Menu ===");
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("Choose an option: ");
    }

    private static void showAllTransactions() {
        System.out.println("\n--- All Transactions ---");
        List<Transaction> transactions = FileDataManger.readTransactions();
        printSortedTransactions(transactions);
    }

    private static void showDeposits() {
        System.out.println("\n--- Deposits ---");
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> deposits = new ArrayList<>();

        for (Transaction t : allTransactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }

        printSortedTransactions(deposits);
    }

    private static void showPayments() {
        System.out.println("\n--- Payments ---");
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> payments = new ArrayList<>();

        for (Transaction t : allTransactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }

        printSortedTransactions(payments);
    }

    private static void printSortedTransactions(List<Transaction> transactions) {
        // Sorting using a simple bubble sort algorithm
        for (int i = 0; i < transactions.size(); i++) {
            for (int j = 0; j < transactions.size() - 1 - i; j++) {
                Transaction t1 = transactions.get(j);
                Transaction t2 = transactions.get(j + 1);

                // Compare dates first, and if they are the same, compare times
                int dateCompare = t2.getDate().compareTo(t1.getDate());
                if (dateCompare == 0) {
                    // If dates are the same, compare times
                    int timeCompare = t2.getTime().compareTo(t1.getTime());
                    if (timeCompare > 0) {
                        // Swap if timeCompare is greater
                        transactions.set(j, t2);
                        transactions.set(j + 1, t1);
                    }
                } else if (dateCompare > 0) {
                    // Swap if dateCompare is greater
                    transactions.set(j, t2);
                    transactions.set(j + 1, t1);
                }
            }
        }

        // Print the sorted transactions
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}