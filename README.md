# 💼 Financial Tracker CLI

A Java-based command-line application to track financial transactions (deposits and payments). Built using Java fundamentals, object-oriented programming, and file I/O.

---

## 📌 Project Overview

This app lets users:
- Add deposits and payments
- Save and load transactions from a CSV file
- View a ledger of all activity
- Generate reports by date and vendor

---

## ✅ Main Features

### 🏠 Home Menu
- `D` – Add Deposit  
- `P` – Make Payment  
- `L` – View Ledger  
- `X` – Exit  

### 📒 Ledger Menu
- `A` – View All Transactions  
- `D` – View Deposits Only  
- `P` – View Payments Only  
- `R` – Reports  
- `H` – Return to Home  

### 📊 Reports Menu
- `1` – Month To Date  
- `2` – Previous Month  
- `3` – Year To Date  
- `4` – Previous Year  
- `5` – Search by Vendor  
- `0` – Back to Ledger  

---

## ⚙️ Data Format

Transactions are stored in `transactions.csv` using this format:

## 🧠 Challenges & Solutions

### File Handling  
**Problem:** Making sure the CSV file is created, read, and written properly.  
**Fix:** Used a `FileManager` class with built-in error handling and auto-creation.

### Date & Time Filters  
**Problem:** Filtering data by month and year.  
**Fix:** Used `LocalDate` and `LocalTime` with simple helper methods.

### Searching by Vendor  
**Problem:** Getting results by vendor name.  
**Fix:** Filtered the transaction list using Java Streams.

### Sorting Transactions  
**Problem:** Showing newest transactions first.  
**Fix:** Sorted the list by date and time in descending order.

---

## Screenshots
<img src="./assets/screenshots/img1.png" alt="My Project Screenshot" width="400"/>
<img src="./assets/screenshots/img2.png" alt="My Project Screenshot" width="400"/>
<img src="./assets/screenshots/img3.png" alt="My Project Screenshot" width="400"/>
<img src="./assets/screenshots/img4.png" alt="My Project Screenshot" width="400"/>
<img src="./assets/screenshots/img5.png" alt="My Project Screenshot" width="400"/>

---

## 🔧 Technologies Used

- Java SE
- File I/O
- OOP (Object-Oriented Programming)
