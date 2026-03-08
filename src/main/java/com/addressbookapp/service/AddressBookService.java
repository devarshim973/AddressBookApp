package com.addressbookapp.service;

import com.addressbookapp.model.AddressBook;
import com.addressbookapp.model.Contact;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressBookService {

    private Map<String, AddressBook> addressBooks = new HashMap<>();

    public Contact addContact(String bookName, Contact contact) {

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {
            book = new AddressBook(bookName);
            addressBooks.put(bookName, book);
        }

        book.addContact(contact);

        return contact;
    }

    public Contact updateContact(String bookName,
            String firstName,
            String lastName,
            Contact updatedContact) {

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {
            return null;
        }

        for (Contact contact : book.getContacts()) {

            if (contact.getFirstName().equals(firstName) &&
                    contact.getLastName().equals(lastName)) {

                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());
                contact.setPhoneNo(updatedContact.getPhoneNo());
                contact.setEmail(updatedContact.getEmail());

                return contact;
            }
        }

        return null;
    }
    
    public boolean deleteContact(String bookName,
            String firstName,
            String lastName) {

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {
            return false;
        }

        return book.getContacts().removeIf(contact -> contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName));
    }

    public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }
}