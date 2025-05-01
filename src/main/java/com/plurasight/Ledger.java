package com.plurasight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// THIS IS THE LEDGER CLASS TO DISPLAY ALL THE ENTRIES, DEPOSITES, PAYMENTS AND REPORTS
public class Ledger {
    private static Scanner InputScanner = new Scanner(System.in);

    // THIS METHOD TO DISPLAY THE LEDGER MENU AND A PROMPT FOR THE USER TO SELECT AN OPTION
    public static void showLedgerMenu() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = InputScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":  // A) All Transactions
                    showAllTransactions();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
                case "R":
                    ReportMenu.showReportMenu();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    //=====================================================================================================
    private static void printMenu() {

        System.out.println("\n=== Ledger Menu ===");
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("Choose an option: ");
    }

    //=====================================================================================================
    // THIS METHOD IS TO GRAP THE TRANSACTION LIST FROM THE readTransaction METHOD IN THE FileDataManger
    private static void showAllTransactions() {
        System.out.println("\n--- All Transactions ---");
        List<Transaction> transactions = FileDataManger.readTransactions();
        printTransactions(transactions);
    }

    //=====================================================================================================
    // THIS METHOD TO SHOW ALL THE DEPOSIT IN EACH TRANSACTION
    private static void showDeposits() {
        System.out.println("\n--- Deposits ---");

        // ASSIGNING THE readTransaction METHODS TO THE allTransaction VARIABLE (ARRAY)
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> deposits = new ArrayList<>();

        // LOOPING OVER THE allTransaction ARRAY WHICH HAS ALL THE RETURNED ALL THE TRANSACTIONS
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }

        printTransactions(deposits);
    }

    //=====================================================================================================
    // THIS METHOD TO DISPLAY ALL THE PAYMENT IN EACH TRANSACTION
    private static void showPayments() {
        System.out.println("\n--- Payments ---");

        // ASSIGNING THE readTransaction METHODS TO THE allTransaction VARIABLE (ARRAY)
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> payments = new ArrayList<>();

        // LOOPING OVER THE allTransaction ARRAY WHICH HAS ALL THE RETURNED ALL THE TRANSACTION
        for (Transaction transaction : allTransactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }

        printTransactions(payments);
    }


    //=====================================================================================================
    // THIS METHOD IS TO LOOP OVER THE ARRAY OF THE TRANSACTION TO DISPLAY THE DATA INFORMATION IN ANY OTHER METHODS
    public static void printTransactions(List<Transaction> transactions) {
       // LOOP OVER EACH TRANSACTION
        for (int i = 0; i < transactions.size(); i++) {
            // CREATING THE NESTED LOOP TO DO COMPARE WITH EACH TRANSACTION IN THE LIST
            for (int j = 0; j < transactions.size() - 1 - i; j++) {
                // GET A SPECIFIC TRANSACTION TO COMPARE
                Transaction transaction1 = transactions.get(j);
                Transaction transaction2 = transactions.get(j + 1);

                // GET THE DATE AND COMPARE BETWEEN THEM
                // IF THEY ARE THE SAME, THEN I DO COMPARE BETWEEN THE TIME
                int dateCompare = transaction2.getDate().compareTo(transaction1.getDate());
                if (dateCompare == 0) {
                    // IF DATE ARE SAME THEN COMPARE TIME
                    int timeCompare = transaction2.getTime().compareTo(transaction1.getTime());
                    if (timeCompare > 0) {
                        // SWAP BETWEEN THEM
                        transactions.set(j, transaction2);
                        transactions.set(j + 1, transaction1);
                    }
                } else if (dateCompare > 0) {
                    // SWAP BETWEEN THEM IF DATE IS GREATER
                    transactions.set(j, transaction2);
                    transactions.set(j + 1, transaction1);
                }
            }
        }

        // LOOP OVER THE SORTED TRANSACTION AND PRINTED THEM
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}