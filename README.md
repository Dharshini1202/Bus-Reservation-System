# 🚌 Bus Reservation System (Java + JDBC + MySQL)

## 📌 Overview

This project is a **Bus Reservation System** developed using **Core Java, JDBC, and MySQL**.
It allows users to view buses, add new buses, book tickets, cancel bookings, and manage data through a simple interface.

The system follows a **layered architecture (Model–DAO–UI)** and demonstrates database connectivity using JDBC.

---

## 🛠️ Technologies Used

* Core Java
* JDBC (Java Database Connectivity)
* MySQL Database
* IntelliJ IDEA (IDE)
* Java Swing (GUI)

---

## 🧱 Project Structure

```
BusReservationSystem
│
├── src/
│   ├── model/
│   │   ├── Bus.java
│   │   └── User.java
│   │
│   ├── dao/
│   │   ├── BusDAO.java
│   │   └── BookingDAO.java
│   │
│   ├── util/
│   │   └── DBConnection.java
│   │
│   ├── BusUI.java
│   ├── Main.java
│   └── db.properties
│
└── README.md
```

---

## 🗄️ Database Setup

### 1. Create Database

```sql
CREATE DATABASE bus_reservation;
USE bus_reservation;
```

### 2. Create Tables

```sql
CREATE TABLE buses (
    Bus_ID INT PRIMARY KEY AUTO_INCREMENT,
    Bus_Name VARCHAR(50),
    Source VARCHAR(50),
    Destination VARCHAR(50),
    Available_Seats INT
);

CREATE TABLE users (
    User_ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50)
);

CREATE TABLE bookings (
    Booking_ID INT PRIMARY KEY AUTO_INCREMENT,
    Bus_ID INT,
    User_ID INT,
    Booking_Date DATE,
    Seats_Booked INT,
    FOREIGN KEY (Bus_ID) REFERENCES buses(Bus_ID),
    FOREIGN KEY (User_ID) REFERENCES users(User_ID)
);
```

---

## 🔐 Database Configuration

Create a file named:

```
db.properties
```

Add the following:

```properties
db.url=jdbc:mysql://localhost:3306/bus_reservation
db.user=root
db.password=YOUR_PASSWORD
```

⚠️ Replace `YOUR_PASSWORD` with your MySQL password before running.

---

## ▶️ How to Run

1. Start MySQL Server
2. Open project in IntelliJ IDEA
3. Add MySQL Connector (JDBC)
4. Run:

👉 `BusUI.java` (Recommended GUI version)

OR

👉 `Main.java` (Console version)

---

## ⚙️ Features

### ✅ View Buses

Displays all available buses.

### ✅ Add Bus

Add new buses dynamically from UI.

### ✅ Book Ticket

* Enter name, bus ID, and seats
* Checks seat availability
* Updates database

### ✅ View Bookings

Displays all bookings.

### ✅ Cancel Ticket

* Cancel using Booking ID
* Restores seats automatically

---

## 🔄 System Flow

1. User interacts via UI
2. DAO layer handles logic
3. JDBC connects to MySQL
4. Data is stored/retrieved from database

---

## 🎯 Concepts Used

* Object-Oriented Programming (OOP)
* JDBC Connectivity
* SQL (CRUD operations)
* Exception Handling
* Layered Architecture (Model, DAO, Utility)
* Java Swing (GUI)
* Collections (List)

---

## 💡 Key Highlights

* Dynamic database-driven system
* Real-time seat updates
* Clean modular structure
* Both Console & GUI versions
* Beginner-friendly + scalable

