package com.addressbookapp.console;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        System.out.println("Welcome to Address Book Program");

        String choice;

        do{
            Contact contact = readContact(sc);
            addressBook.addContact(contact);

            System.out.print("Do you want to add another contact? (yes/no): ");
            choice = sc.nextLine();

        } while(choice.equalsIgnoreCase("yes"));

        addressBook.displayContact();
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
}