package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        System.out.println("Welcome to Address Book Program");

        // Add first contact
        System.out.println("Enter first contact details:");

        System.out.print("First Name: ");
        String firstName1 = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName1 = sc.nextLine();

        System.out.print("Address: ");
        String address1 = sc.nextLine();

        System.out.print("City: ");
        String city1 = sc.nextLine();

        System.out.print("State: ");
        String state1 = sc.nextLine();

        System.out.print("Zip: ");
        String zip1 = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber1 = sc.nextLine();

        System.out.print("Email: ");
        String email1 = sc.nextLine();

        Contact contact1 = new Contact(firstName1, lastName1, address1, city1, state1, zip1, phoneNumber1, email1);
        addressBook.addContact(contact1);

        // Add second contact
        System.out.println("\nEnter second contact details:");

        System.out.print("First Name: ");
        String firstName2 = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName2 = sc.nextLine();

        System.out.print("Address: ");
        String address2 = sc.nextLine();

        System.out.print("City: ");
        String city2 = sc.nextLine();

        System.out.print("State: ");
        String state2 = sc.nextLine();

        System.out.print("Zip: ");
        String zip2 = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber2 = sc.nextLine();

        System.out.print("Email: ");
        String email2 = sc.nextLine();

        Contact contact2 = new Contact(firstName2, lastName2, address2, city2, state2, zip2, phoneNumber2, email2);
        addressBook.addContact(contact2);

        System.out.println("\nAll Contacts:");
        addressBook.displayContact();

        // Delete by name
        System.out.print("\nEnter first name of contact to delete: ");
        String nameToDelete = sc.nextLine();

        boolean isDeleted = addressBook.deleteContactByFirstName(nameToDelete);

        if(isDeleted) {
            System.out.println("Contact deleted successfully.");
        }else {
            System.out.println("Contact not found.");
        }

        System.out.println("\nRemaining Contacts:");
        addressBook.displayContact();

    }
}