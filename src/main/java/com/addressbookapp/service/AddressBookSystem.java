package com.addressbookapp.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class AddressBookSystem {

    private final Map<String, AddressBook> addressBookMap = new LinkedHashMap<>();

    public boolean addAddressBook(String addressBookName) {
        if(addressBookMap.containsKey(addressBookName)) {
            return false;
        }

        addressBookMap.put(addressBookName, new AddressBook());
        return true;
    }

    public AddressBook getAddressBook(String addressBookName) {
        return addressBookMap.get(addressBookName);
    }

    public void displayAddressBooks() {
        if(addressBookMap.isEmpty()) {
            System.out.println("No address books found.");
            return;
        }

        System.out.println("\nAvailable Address Books:");
        for(String name : addressBookMap.keySet()) {
            System.out.println(name);
        }
    }

    public Map<String, AddressBook> getAddressBookMap() {
        return addressBookMap;
    }
}