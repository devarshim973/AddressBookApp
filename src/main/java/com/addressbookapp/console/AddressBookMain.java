package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;
import com.addressbookapp.service.AddressBookSystem;

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

        sc.close();
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
}