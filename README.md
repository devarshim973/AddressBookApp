# AddressBookApp📂

## Overview

**AddressBookApp** is a comprehensive Java-based application developed using **Maven**, **JDBC**, **File I/O**, **Multithreading**, and **REST API Testing**.
The project demonstrates multiple core backend engineering concepts including **Object-Oriented Programming, Collections, Streams API, File Handling, Database Operations, Concurrency, and REST API automation**.

The application evolves step-by-step through multiple feature branches (UC1–UC27) implementing increasingly advanced capabilities.

This project is structured using **Git branching strategies** where each feature is developed in its own branch and merged into the main branch after completion.

---

## Project Goals

The project is designed to demonstrate the following technical skills:

* Object Oriented Design in Java
* Java Collections Framework
* Java Streams API
* File Handling (TXT, CSV, JSON)
* JDBC Database Integration
* Transaction Management
* Multithreading
* REST API Testing using REST Assured
* Test Driven Development using JUnit
* Git Version Control & Feature Branch Workflow

---

## Technology Stack♨️

| Technology   | Purpose                               |
| ------------ | ------------------------------------- |
| Java         | Core Programming Language             |
| Maven        | Project Build & Dependency Management |
| JUnit        | Unit Testing Framework                |
| JDBC         | Database Connectivity                 |
| MySQL        | Database Storage                      |
| Java Streams | Data processing                       |
| OpenCSV      | CSV File Handling                     |
| Gson         | JSON Serialization / Deserialization  |
| REST Assured | REST API Testing                      |
| JSON Server  | Mock REST Backend                     |
| Git          | Version Control                       |
| Spring Boot  | Backend Development                   |

---

## Project Architecture🏗️

```
📦 AddressBookApp
│
├── 📁 .git
├── 📁 .mvn
│
├── 📁 src
│   │
│   ├── 📁 main
│   │   │
│   │   ├── 📁 java
│   │   │   └── 📁 com
│   │   │       └── 📁 addressbook
│   │   │           │
│   │   │           ├── 📁 controller
│   │   │           │   └── 📄 AddressBookController.java
│   │   │           │
│   │   │           ├── 📁 dto
│   │   │           │   └── 📄 ContactDTO.java
│   │   │           │
│   │   │           ├── 📁 model
│   │   │           │   ├── 📄 Contact.java
│   │   │           │   └── 📄 AddressBook.java
│   │   │           │
│   │   │           ├── 📁 repository
│   │   │           │   └── 📄 ContactRepository.java
│   │   │           │
│   │   │           ├── 📁 service
│   │   │           │   └── 📄 AddressBookService.java
│   │   │           │
│   │   │           ├── 📁 storage
│   │   │           │   ├── 📄 ContactStorage.java
│   │   │           │   ├── 📄 FileStorage.java
│   │   │           │   ├── 📄 CSVStorage.java
│   │   │           │   └── 📄 JSONStorage.java
│   │   │           │
│   │   │           ├── 📁 threads
│   │   │           │   └── 📄 AddContactTask.java
│   │   │           │
│   │   │           ├── 📁 util
│   │   │           │   ├── 📄 AddressBookFileIO.java
│   │   │           │   ├── 📄 AddressBookCSVIO.java
│   │   │           │   └── 📄 AddressBookJSONIO.java
│   │   │           │
│   │   │           └── 📄 AddressBookApplication.java
│   │   │
│   │   └── 📁 resources
│   │       └── 📄 application.properties
│   │
│   └── 📁 test
│       └── 📁 java
│           └── 📁 com
│               └── 📁 addressbook
│                   │
│                   ├── 📄 AddressBookApplicationTests.java
│                   ├── 📁 repository
│                   |    └── 📄 ContactRepositoryTest.java
|                   ├── 📁 service
│                   |    └── 📄 AddressBookServiceTest.java
|                   ├── 📁 util
│                   |    └── 📄 AddressBookJsonServerTest.java
|                   |
|                   └── 📄 AddressBookApplicationTests.java
│
├── ⚙️ pom.xml
│
├── 📄 mvnw
├── 📄 mvnw.cmd
│
├── 📄 .gitattributes
├── 🚫 .gitignore
│
├── 📜 LICENSE
└── 📘 README.md
```

---

## Branch Strategy🌿

This repository follows a **feature branch workflow**.

```
main
  │
  └── dev
        │
        ├── feature/uc-01-contact-model
        ├── feature/uc-02-add-contact-console
        ├── feature/uc-03-edit-contact-by-name
        ├── feature/uc-04-delete-contact-by-name
        ├── feature/uc-05-multiple-contacts-collection
        ├── feature/uc-06-multiple-addressbooks-map
        ├── feature/uc-07-prevent-duplicate-entry
        ├── feature/uc-08-search-person-by-city-state
        ├── feature/uc-09-view-persons-by-city-state
        ├── feature/uc-10-count-contacts-by-city-state
        ├── feature/uc-11-sort-contacts-by-name
        ├── feature/uc-12-sort-contacts-by-city-state-zip
        ├── feature/uc-13-file-io-read-write
        ├── feature/uc-14-csv-read-write-opencsv
        ├── feature/uc-15-json-read-write-gson
        ├── feature/uc-16-jdbc-read-all
        ├── feature/uc-17-jdbc-update-contact-sync
        ├── feature/uc-18-jdbc-retrieve-by-date-range
        ├── feature/uc-19-jdbc-count-by-city-state
        ├── feature/uc-20-jdbc-add-contact-transaction
        ├── feature/uc-21-multithreaded-db-insert
        ├── feature/uc-22-restassured-read-jsonserver
        ├── feature/uc-23-restassured-add-contact
        ├── feature/uc-24-restassured-update-contact
        ├── feature/uc-25-restassured-delete-contact
        ├── feature/uc-26-nonblocking-io-threads
        └── feature/uc-27-open-close-principle-datasource
```

Each use case is developed independently and merged into `dev`, then integrated into `main`.

---

## Implemented Functionalities

### Section 1 — Address Book Core (UC1–UC12)

* Create Contact Model
* Add / Edit / Delete Contacts
* Prevent Duplicate Contacts
* Manage Multiple Address Books
* Search Contacts by City/State
* Count Contacts by City/State
* Sort Contacts by Name, City, State, Zip
* Java Streams API usage

---

### Section 2 — File Handling (UC13–UC15)

#### File IO

Contacts can be stored in text files.

#### CSV Support

Contacts exported/imported using **OpenCSV**.

#### JSON Support

Contacts exported/imported using **Gson**.

---

### Section 3 — JDBC Database Integration (UC16–UC20)

#### Database Features

* Retrieve Contacts from Database
* Update Contact Information
* Retrieve Contacts by Date Range
* Count Contacts by City/State using SQL Aggregation
* Insert Contacts using Database Transactions

---

### Section 4 — Multithreading (UC21)

Multiple contacts are inserted into the database concurrently using Java threads.

Features:

* Parallel Database Inserts
* Thread Management
* Transaction Safety

---

### Section 5 — REST API Testing (UC22–UC25)

REST Assured is used to interact with a mock REST backend powered by JSON Server.

Operations implemented:

* Read Contacts from JSON Server
* Add Contacts
* Update Contacts
* Delete Contacts

---

### Section 6 — Advanced Design (UC26–UC27)

#### UC26

Ensure IO operations do not block the main thread using background threads.

#### UC27

Implement **Open-Closed Principle** so new data sources can be added without modifying existing code.

Supported Data Sources:

* CSV Files
* JSON Files
* Database
* JSON Server

---

## Database Schema

### Address Books Table

```sql
CREATE TABLE address_books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL
);
```

### Contacts Table

```sql
CREATE TABLE contacts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    zip VARCHAR(20),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    date_added DATE,
    address_book_id INT,
    FOREIGN KEY(address_book_id) REFERENCES address_books(id)
);
```

---

## Running JSON Server (For REST Testing)

Install JSON Server:

```bash
npm install -g json-server
```

Start server:

```bash
json-server --watch json-server/db.json --port 3000
```

API Endpoint:

```
http://localhost:3000/contacts
```

---

## Running the Project

Clone repository:

```bash
git clone https://github.com/your-username/AddressBookApp.git
```

Run using Maven:

```bash
mvn clean install
```

Run tests:

```bash
mvn test
```

---

## Learning Outcomes       

After completing this project you will gain hands-on experience with:

* Clean Java Architecture
* File Processing
* JDBC Database Programming
* Transaction Management
* Multithreaded Programming
* REST API Testing
* Git Branching Strategies
* Software Design Principles
