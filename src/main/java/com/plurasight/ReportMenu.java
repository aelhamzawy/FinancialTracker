package com.plurasight;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportMenu {

    private static  Scanner InputScanner = new Scanner(System.in);

    public static void showReportMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Report Menu ===");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            System.out.print("Choose an option: ");
            String choice = InputScanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showMonthToDate();
                    break;
                case "2":
                    showPreviousMonth();
                    break;
                case "3":
                    showYearToDate();
                    break;
                case "4":
                    showPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMonthToDate() {
        System.out.println("\n--- Month To Date ---");
        LocalDate now = LocalDate.now();
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getDate().getMonth() == now.getMonth() && transaction.getDate().getYear() == now.getYear()) {
                result.add(transaction);
            }
        }

        Ledger.printTransactions(result);
    }

    private static void showPreviousMonth() {
        System.out.println("\n--- Previous Month ---");
        LocalDate now = LocalDate.now();
        LocalDate startOfPreviousMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfPreviousMonth = now.withDayOfMonth(1).minusDays(1);

        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            LocalDate date = transaction.getDate();
            if (!date.isBefore(startOfPreviousMonth) && !date.isAfter(endOfPreviousMonth)) {
                result.add(transaction);
            }
        }

        Ledger.printTransactions(result);
    }

    private static void showYearToDate() {
        System.out.println("\n--- Year To Date ---");
        LocalDate now = LocalDate.now();
        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getDate().getYear() == now.getYear()) {
                result.add(transaction);
            }
        }

        Ledger.printTransactions(result);
    }

    // METHOD TO GET THE PREVIOUS YEAR
    private static void showPreviousYear() {
        System.out.println("\n--- Previous Year ---");
        LocalDate now = LocalDate.now();
        int previousYear = now.getYear() - 1;

        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getDate().getYear() == previousYear) {
                result.add(transaction);
            }
        }

        Ledger.printTransactions(result);
    }

    // METHOD TO GET THE TRANSACTION BY THE VENDOR NAME
    private static void searchByVendor() {
        System.out.print("Enter Vendor Name: ");
        String vendor = InputScanner.nextLine().trim();
        System.out.println("\n--- Transactions for Vendor: " + vendor + " ---");

        List<Transaction> allTransactions = FileDataManger.readTransactions();
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                result.add(transaction);
            }
        }

        Ledger.printTransactions(result);
    }
}
