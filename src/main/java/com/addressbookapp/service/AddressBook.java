package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private final List<Contact> contactList = new ArrayList<>();

    // Add Contacts
    public void addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact added successfully.");
    }

    // Display Contacts -> Shows all information of a Person
    public void displayContact() {
        if(contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for(Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    // Search details of any Person by their FirstName
    public Contact findContactByFirstName(String firstName) {
        for(Contact contact : contactList) {
            if(contact.getFirstName().equalsIgnoreCase(firstName)) {
                return contact;
            }
        }
        return null;
    }

    public boolean editContactByFirstName(String firstName, 
    		String newLastName,
            String newAddress,
            String newCity,
            String newState,
            String newZip,
            String newPhoneNumber,
            String newEmail) 
    {
    	for(Contact contact : contactList) {
    		if(contact.getFirstName().equalsIgnoreCase(firstName)) {
    			contact.setLastName(newLastName);
    			contact.setAddress(newAddress);
    			contact.setCity(newCity);
    			contact.setState(newState);
    			contact.setZip(newZip);
    			contact.setPhoneNo(newPhoneNumber);
    			contact.setEmail(newEmail);
    			return true;
    		}
    	}
    	return false;
    }
}