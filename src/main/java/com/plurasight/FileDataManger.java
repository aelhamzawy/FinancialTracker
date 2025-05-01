package com.plurasight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

// THIS IS THE FILE-DATA-MANGER CLASS WHICH PERFORM THE READS AND WRITE FUNCTIONALITY
public class FileDataManger {

    // CREATE THE FILE PATH WHICH WILL HOLD ALL THE TRANSACTION DATA
    private static String FilePath = "transactions.csv";

    // METHOD TO READ ALL THE DATA FROM THE "transactions.csv" FILE
    // THIS METHOD CREATE THE FILE IF NOT EXIST AND LOOP OVER THE DATA IN IT TO READ IT
    public static List<Transaction> readTransactions() {

        // CREATE THE ARRAY OF TRANSACTION TO HOLD ALL THE DATA
        List<Transaction> transactions = new ArrayList<>();

        try {
            // CREATE THE FILE IF NOT EXIST
            File file = new File(FilePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            // READ THE DATA IN THE FILE
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            // LOOPING OVER THE DATA IN THE FILE TO DISPLAY THE DATA
            while ((line = reader.readLine()) != null) {
                String[] lineDataParts = line.split("\\|");
                if (lineDataParts.length == 5) {

                    // CREATING A NEW OBJECT OF THE TRANSACTION CLASS
                    // AND ASSIGNING EACH PART OF THE LINE WITH A SPECIFIC DATA
                    Transaction transaction = new Transaction(
                            LocalDate.parse(lineDataParts[0]),
                            LocalTime.parse(lineDataParts[1]),
                            lineDataParts[2],
                            lineDataParts[3],
                            Double.parseDouble(lineDataParts[4])
                    );
                    transactions.add(transaction);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error: Some Error Just Happened");
        }

        return transactions;
    }

    // WRITE A NEW TRANSACTION TO THE FILE WHICH HAS BEEN CREATED
    public static void writeTransaction(Transaction transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath, true));
            String line = transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount();
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: Some Error Just Happened");
        }
    }
}
