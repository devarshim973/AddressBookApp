package com.addressbookapp.service;

import com.addressbookapp.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookDBServiceTest {

    @Test
    public void givenContactInDB_WhenRetrieved_ShouldReturnContactList() {
        AddressBookDBService dbService = new AddressBookDBService();
        Assertions.assertTrue(dbService.getAllContactsFromDB().size() > 0);
    }

    @Test
    public void givenContact_WhenCityUpdated_ShouldSyncWithDatabase() {
        AddressBookDBService dbService = new AddressBookDBService();

        Contact memoryContact = dbService.getContactByFirstName("Harshal");
        Assertions.assertNotNull(memoryContact);

        boolean isUpdated = dbService.updateContactCityByName("Harshal", "Pune");
        Assertions.assertTrue(isUpdated);

        memoryContact.setCity("Pune");

        boolean isSynced = dbService.isContactSyncedWithDB(memoryContact);
        Assertions.assertTrue(isSynced);
    }
}