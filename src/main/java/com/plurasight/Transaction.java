package com.plurasight;
import java.time.LocalDate;
import java.time.LocalTime;

// THIS IS THE TRANSACTION CLASS WHICH SHAPE EACH TRANSACTION WHICH IS MADE BY THE VENDOR
// IT SAVES THE DATA & GET THE RETURNED DATA TO DISPLAY IT
public class Transaction {
    // TRANSACTION MAIN VARIABLES
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // CONSTRUCTOR FUNCTION TO POINT TO THE VARIABLES VALUE WEN I CALL THIS CLASS
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // ========= GETTER FUNCTION TO GET ALL THE RETURNED INFORMATION =========

    // METHOD TO GET THE TRANSACTION DATE
    public LocalDate getDate() {
        return date;
    }

    // METHOD TO GET THE TRANSACTION TIME
    public LocalTime getTime() {
        return time;
    }

    // METHOD TO GET THE TRANSACTION DESCRIPTION
    public String getDescription() {
        return description;
    }

    // METHOD TO GET THE TRANSACTION VENDOR NAME
    public String getVendor() {
        return vendor;
    }

    // METHOD TO GET THE AMOUNT WHICH HAS BEEN DEPOSITED
    public double getAmount() {
        return amount;
    }

    // METHODS TO RETURN AND DISPLAY THE DATA WHICH HAS BEEN SAVED IN THE FILE
    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}