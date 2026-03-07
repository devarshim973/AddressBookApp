package com.addressbookapp.service;

import com.addressbookapp.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressBookDBServiceTest {

    @Test
    public void givenDateRange_WhenContactsRetrieved_ShouldReturnOnlyContactsWithinRange() {
        AddressBookDBService dbService = new AddressBookDBService();

        LocalDate startDate = LocalDate.of(2026, 3, 1);
        LocalDate endDate = LocalDate.of(2026, 3, 7);

        List<Contact> contacts = dbService.getContactsAddedBetweenDates(startDate, endDate);

        Assertions.assertNotNull(contacts);

        Assertions.assertTrue(
                contacts.stream().allMatch(contact ->
                        contact.getDateAdded() != null &&
                        !contact.getDateAdded().isBefore(startDate) &&
                        !contact.getDateAdded().isAfter(endDate)
                )
        );
    }
    
    @Test
    public void givenNewContact_WhenAddedToDatabase_ShouldBeRetrievable() {
        AddressBookDBService dbService = new AddressBookDBService();

        Contact contact = new Contact();
        contact.setFirstName("TxnUser");
        contact.setLastName("Demo");
        contact.setAddress("Txn Address");
        contact.setCity("Bhopal");
        contact.setState("MP");
        contact.setZip("462001");
        contact.setPhoneNumber("1234567890");
        contact.setEmail("txnuser@gmail.com");
        contact.setDateAdded(LocalDate.now());

        boolean isAdded = dbService.addContactToDatabase("TxnBook", contact);
        Assertions.assertTrue(isAdded);

        Contact dbContact = dbService.getContactByFirstName("TxnUser");
        Assertions.assertNotNull(dbContact);
        Assertions.assertEquals("TxnUser", dbContact.getFirstName());
    }
}