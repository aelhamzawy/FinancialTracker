package com.plurasight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=== Welcome to the Financial Tracker App ===");

        while (running) {
            System.out.println("\n--- Home Menu ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(true);
                    break;
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    Ledger.showLedgerMenu();
                    break;
                case "X":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (!isDeposit) amount *= -1;

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        FileDataManger.writeTransaction(t);

        System.out.println("Transaction saved.");
    }
}
