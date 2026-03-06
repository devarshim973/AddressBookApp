package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

public class AddressBook {

    private Contact contact;

    public void addContact(Contact contact) {
        this.contact = contact;
        System.out.println("Contact added successfully.");
    }

    public void displayContact() {
        if (contact == null) {
            System.out.println("No contact found.");
        } else {
            System.out.println(contact);
        }
    }
}
