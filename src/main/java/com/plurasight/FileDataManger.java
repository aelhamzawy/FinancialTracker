package com.plurasight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class FileDataManger {
    private static final String FILE_PATH = "transactions.csv";

    // Read all transactions from the file
    public static List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try {
            // Create file if it doesn't exist
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split("\\|");
                if (lineParts.length == 5) {
                    Transaction transaction = new Transaction(
                            LocalDate.parse(lineParts[0]),
                            LocalTime.parse(lineParts[1]),
                            lineParts[2],
                            lineParts[3],
                            Double.parseDouble(lineParts[4])
                    );
                    transactions.add(transaction);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return transactions;
    }

    // Add a new transaction to the file
    public static void writeTransaction(Transaction transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            String line = transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount();
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
