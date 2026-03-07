package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, AddressBook> getAddressBookMap() {
        return addressBookMap;
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

    public List<Contact> searchPersonsByCity(String city) {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchPersonsByState(String state) {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public Map<String, List<Contact>> getPersonsByCity() {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }

    public Map<String, List<Contact>> getPersonsByState() {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getState));
    }
    
    public Map<String, Long> getContactCountByCity() {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));
    }

    public Map<String, Long> getContactCountByState() {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));
    }
}