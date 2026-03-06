package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        System.out.println("Welcome to Address Book Program");

        // Add one contact first
        System.out.println("Enter contact details to add:");

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

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        addressBook.addContact(contact);

        System.out.println("\nCurrent Contacts:");
        addressBook.displayContact();

        // Edit contact by first name
        System.out.print("\nEnter first name of contact to edit: ");
        String nameToEdit = sc.nextLine();

        System.out.println("Enter new details:");

        System.out.print("New Last Name: ");
        String newLastName = sc.nextLine();

        System.out.print("New Address: ");
        String newAddress = sc.nextLine();

        System.out.print("New City: ");
        String newCity = sc.nextLine();

        System.out.print("New State: ");
        String newState = sc.nextLine();

        System.out.print("New Zip: ");
        String newZip = sc.nextLine();

        System.out.print("New Phone Number: ");
        String newPhoneNumber = sc.nextLine();

        System.out.print("New Email: ");
        String newEmail = sc.nextLine();

        boolean isEdited = addressBook.editContactByFirstName(
                nameToEdit,
                newLastName,
                newAddress,
                newCity,
                newState,
                newZip,
                newPhoneNumber,
                newEmail
        );

        if(isEdited) {
            System.out.println("\nContact updated successfully.");
        }else {
            System.out.println("\nContact not found.");
        }

        System.out.println("\nUpdated Contacts:");
        addressBook.displayContact();
    }
}