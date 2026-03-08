package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBookRestService {

    private final List<Contact> contactList = new ArrayList<>();

    public void setContacts(List<Contact> contacts) {
        contactList.clear();
        contactList.addAll(contacts);
    }

    public List<Contact> getContacts() {
        return contactList;
    }

    public int getContactCount() {
        return contactList.size();
    }
}