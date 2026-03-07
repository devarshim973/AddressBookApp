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
}