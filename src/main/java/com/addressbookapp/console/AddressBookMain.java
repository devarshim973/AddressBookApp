package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;
import com.addressbookapp.service.AddressBookDBService;
import com.addressbookapp.service.AddressBookSystem;
import com.addressbookapp.util.AddressBookFileIO;
import com.addressbookapp.util.AddressBookCSVIO;
import com.addressbookapp.util.AddressBookJSONIO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBookSystem addressBookSystem = new AddressBookSystem();

        System.out.println("Welcome to Address Book Program");

        String addressBookChoice;

        do{
            System.out.print("\nEnter Address Book Name: ");
            String addressBookName = sc.nextLine();

            boolean isAdded = addressBookSystem.addAddressBook(addressBookName);

            if(!isAdded) {
                System.out.println("Address Book with this name already exists.");
            }else {
                System.out.println("Address Book added successfully.");
            }

            AddressBook addressBook = addressBookSystem.getAddressBook(addressBookName);

            String contactChoice;
            do{
                Contact contact = readContact(sc);
                boolean isContactAdded = addressBook.addContact(contact);

                if(isContactAdded) {
                    System.out.println("Contact added successfully.");
                }else{
                    System.out.println("Duplicate contact found. Contact not added.");
                }

                System.out.print("Do you want to add another contact to " + addressBookName + "? (yes/no): ");
                contactChoice = sc.nextLine();

            } while(contactChoice.equalsIgnoreCase("yes"));

            System.out.print("Do you want to add another Address Book? (yes/no): ");
            addressBookChoice = sc.nextLine();

        }while(addressBookChoice.equalsIgnoreCase("yes"));

        System.out.println("\nPersons Grouped By City:");
        displayGroupedPersons(addressBookSystem.getPersonsByCity());

        System.out.println("\nPersons Grouped By State:");
        displayGroupedPersons(addressBookSystem.getPersonsByState());

        System.out.println("\nContact Count By City:");
        displayCount(addressBookSystem.getContactCountByCity());

        System.out.println("\nContact Count By State:");
        displayCount(addressBookSystem.getContactCountByState());
        
        System.out.println("\nContacts Sorted Alphabetically:");
        List<Contact> sortedContacts = addressBookSystem.getAllContactsSortedByName();
        displayContacts(sortedContacts);
        
        // Sorted by City, State & Zip -> (uc-12)
        System.out.println("\nContacts Sorted By City");
        displayContacts(addressBookSystem.getContactsSortedByCity());
        
        System.out.println("\nContacts Sorted By State");
        displayContacts(addressBookSystem.getContactsSortedByState());
        
        System.out.println("\nContacts Sorted By Zip");
        displayContacts(addressBookSystem.getContactsSortedByZip());
        
        // file-io-read-write -> (uc-13)
        AddressBookFileIO fileIO = new AddressBookFileIO();
        String fileName = "src/main/resources/files/addressbook-data.txt";

        fileIO.writeContactsToFile(fileName, addressBookSystem.getAllContacts());
        fileIO.readContactsFromFile(fileName);
        
        // csv-read-write-opencsv -> (uc-14)
        AddressBookCSVIO csvIO = new AddressBookCSVIO();
        String csvFile = "src/main/resources/files/addressbook-data.csv";

        csvIO.writeContactsToCSV(csvFile, addressBookSystem.getAllContacts());
        csvIO.readContactsFromCSV(csvFile);
        
        // json-read-write-gson -> (uc-15)
        AddressBookJSONIO jsonIO = new AddressBookJSONIO();
        String jsonFile = "src/main/resources/files/addressbook-data.json";

        jsonIO.writeContactsToJSON(jsonFile, addressBookSystem.getAllContacts());
        jsonIO.readContactsFromJSON(jsonFile);
        
        // JDBC -> (uc-16)
        AddressBookDBService dbService = new AddressBookDBService();
        List<Contact> dbContacts = dbService.getAllContactsFromDB();

        System.out.println("\nContacts Retrieved From Database:");
        for (Contact contact : dbContacts) {
            System.out.println(contact);
        }
        
        // JDBC -> (uc-17)
        // This is optional, but useful for your own check.
        AddressBookDBService dbService2 = new AddressBookDBService();

        Contact contact = dbService2.getContactByFirstName("Harshal");
        System.out.println("Before Update: " + contact);

        boolean updated = dbService2.updateContactCityByName("Harshal", "Pune");

        if(updated && contact != null) {
            contact.setCity("Pune");
        }

        System.out.println("Is Contact Synced With DB? " + dbService2.isContactSyncedWithDB(contact));
        System.out.println("After Update From DB: " + dbService2.getContactByFirstName("Harshal"));
        
        // JDBC -> (uc-18)
        // This is only for testing
        AddressBookDBService dbService3 = new AddressBookDBService();

        List<Contact> contactsByDate = dbService3.getContactsAddedBetweenDates(
                LocalDate.of(2026, 3, 1),
                LocalDate.of(2026, 3, 7)
        );

        System.out.println("\nContacts added between 2026-03-01 and 2026-03-07:");
        for(Contact con : contactsByDate) {
            System.out.println(con);
        }
        
        // JDBC -> (uc-19)
        // This is only for testing
        // Count by City
        Map<String, Integer> cityCount = dbService.getContactCountByCity();

        System.out.println("\nContact Count By City From Database:");
        for(Map.Entry<String, Integer> entry : cityCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        // Count by State
        Map<String, Integer> stateCount = dbService.getContactCountByState();

        System.out.println("\nContact Count By State From Database:");
        for (Map.Entry<String, Integer> entry : stateCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static Contact readContact(Scanner sc) {
        System.out.println("\nEnter Contact Details:");

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("State: ");
        String state = sc.nextLine();

        System.out.print("Zip: ");
        String zip = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }

    private static void displayGroupedPersons(Map<String, List<Contact>> groupedData) {
        if(groupedData.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }

        for(Map.Entry<String, List<Contact>> entry : groupedData.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for(Contact contact : entry.getValue()) {
                System.out.println(contact);
            }
        }
    }
    
    private static void displayCount(Map<String, Long> countMap) {

        if(countMap.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for(Map.Entry<String, Long> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
    
    private static void displayContacts(List<Contact> contacts) {

        if(contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for(Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}