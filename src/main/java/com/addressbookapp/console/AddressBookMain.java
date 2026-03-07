package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;
import com.addressbookapp.service.AddressBookSystem;

import java.util.List;
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
            }else{
                System.out.println("Address Book added successfully.");
            }

            AddressBook addressBook = addressBookSystem.getAddressBook(addressBookName);

            String contactChoice;
            do{
                Contact contact = readContact(sc);
                boolean isContactAdded = addressBook.addContact(contact);

                if(isContactAdded) {
                    System.out.println("Contact added successfully.");
                }else {
                    System.out.println("Duplicate contact found. Contact not added.");
                }

                System.out.print("Do you want to add another contact to " + addressBookName + "? (yes/no): ");
                contactChoice = sc.nextLine();

            } while(contactChoice.equalsIgnoreCase("yes"));

            System.out.print("Do you want to add another Address Book? (yes/no): ");
            addressBookChoice = sc.nextLine();

        } while(addressBookChoice.equalsIgnoreCase("yes"));

        // Search by city
        System.out.print("\nEnter city to search persons: ");
        String city = sc.nextLine();

        List<Contact> cityResults = addressBookSystem.searchPersonsByCity(city);
        System.out.println("\nPersons found in city: " + city);
        displaySearchResults(cityResults);

        // Search by state
        System.out.print("\nEnter state to search persons: ");
        String state = sc.nextLine();

        List<Contact> stateResults = addressBookSystem.searchPersonsByState(state);
        System.out.println("\nPersons found in state: " + state);
        displaySearchResults(stateResults);
        
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

    private static void displaySearchResults(List<Contact> contacts) {
        if(contacts.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }

        for(Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}