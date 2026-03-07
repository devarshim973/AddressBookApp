package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private final List<Contact> contactList = new ArrayList<>();

    public void addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact added successfully.");
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void displayContact() {
        if(contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\nAll Contacts:");
        for(Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    public boolean editContactByFirstName(String firstName,
                                          String newLastName,
                                          String newAddress,
                                          String newCity,
                                          String newState,
                                          String newZip,
                                          String newPhoneNumber,
                                          String newEmail) {
        for(Contact contact : contactList) {
            if(contact.getFirstName().equalsIgnoreCase(firstName)) {
                contact.setLastName(newLastName);
                contact.setAddress(newAddress);
                contact.setCity(newCity);
                contact.setState(newState);
                contact.setZip(newZip);
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    public boolean deleteContactByFirstName(String firstName) {
        for(int i=0; i<contactList.size(); i++) {
            Contact contact = contactList.get(i);
            if(contact.getFirstName().equalsIgnoreCase(firstName)) {
                contactList.remove(i);
                return true;
            }
        }
        return false;
    }
}