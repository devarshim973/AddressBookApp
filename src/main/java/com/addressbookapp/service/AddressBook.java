package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private final List<Contact> contactList = new ArrayList<>();

    // Add Contacts
    public boolean addContact(Contact contact) {
        boolean isDuplicate = contactList.stream()
                .anyMatch(existingContact -> existingContact.equals(contact));

        if(isDuplicate) {
            return false;
        }

        contactList.add(contact);
        return true;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    // Print Contacts
    public void displayContact() {
        if(contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for(Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    // Update Contacts by First Name
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

    // Deleting Contact using First Name
    public boolean deleteContactByFirstName(String firstName) {
        return contactList.removeIf(contact ->
                contact.getFirstName().equalsIgnoreCase(firstName));
    }

    // Check if Person exists or not
    public boolean isPersonExists(String firstName, String lastName) {
        return contactList.stream().anyMatch(contact ->
                contact.getFirstName().equalsIgnoreCase(firstName)
                        && contact.getLastName().equalsIgnoreCase(lastName));
    }
}