package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.Comparator;
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
    
    public List<Contact> getAllContactsSortedByName() {

        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .sorted((c1, c2) -> {
                    int firstNameCompare = c1.getFirstName()
                            .compareToIgnoreCase(c2.getFirstName());

                    if(firstNameCompare != 0) {
                        return firstNameCompare;
                    }

                    return c1.getLastName()
                            .compareToIgnoreCase(c2.getLastName());
                })
                .collect(Collectors.toList());
    }
    
    // Sort by city -> (uc-12)
    public List<Contact> getContactsSortedByCity() {

        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContactList().stream())
                .sorted(Comparator.comparing(Contact::getCity,
                        String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    
    // Sort by State -> (uc-12)
    public List<Contact> getContactsSortedByState() {

        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContactList().stream())
                .sorted(Comparator.comparing(Contact::getState,
                        String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    
    // Sort by Zip -> (uc-12)
    public List<Contact> getContactsSortedByZip() {

        return addressBookMap.values()
                .stream()
                .flatMap(book -> book.getContactList().stream())
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }
}