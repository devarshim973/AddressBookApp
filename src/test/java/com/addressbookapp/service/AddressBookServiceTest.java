package com.addressbookapp.service;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

    private Contact createContact() {

        return new Contact(
                "devarshi",
                "mishra",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );
    }

    @Test
    public void givenValidContact_whenAdded_shouldReturnSameContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = createContact();

        Contact result = service.addContact("personal", contact);

        assertEquals("Tarus", result.getFirstName());
        assertEquals("Prabhat", result.getLastName());
    }

    @Test
    public void givenContact_whenAdded_shouldCreateAddressBookAutomatically() {

        AddressBookService service = new AddressBookService();

        Contact contact = createContact();

        service.addContact("office", contact);

        assertNotNull(service.getAddressBook("office"));
    }

    @Test
    public void givenMultipleContacts_whenAdded_shouldStoreAllContacts() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("personal", c2);

        assertEquals(2,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenDifferentAddressBooks_whenAddingContacts_shouldSeparateData() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("office", c2);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());

        assertEquals(1,
                service.getAddressBook("office").getContacts().size());
    }

    @Test
    public void givenContactWithNullValues_whenAdded_shouldNotCrash() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact();

        Contact result = service.addContact("personal", contact);

        assertNotNull(result);
    }

    @Test
    public void givenContactWithEmptyStrings_whenAdded_shouldStoreSuccessfully() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenSameContactTwice_whenAdded_shouldAllowDuplicatesForNow() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("personal", c2);

        assertEquals(2,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenLongPhoneNumber_whenAdded_shouldStoreContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
        		"Harsh",
                "Choudhary",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenInvalidEmail_whenAdded_shouldStillStoreContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
        		"Harsh",
                "Choudhary",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenMultipleAddressBooks_whenContactsAdded_shouldMaintainSeparateLists() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("family", c1);
        service.addContact("friends", c2);

        assertEquals(1,
                service.getAddressBook("family").getContacts().size());

        assertEquals(1,
                service.getAddressBook("friends").getContacts().size());
    }
    
    @Test
    public void givenExistingContact_whenUpdated_shouldReturnUpdatedContact() {

        AddressBookService service = new AddressBookService();

        Contact original = new Contact(
        		"Harsh",
                "Choudhary",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );

        service.addContact("personal", original);

        Contact updated = new Contact(
        		"Harsh",
                "Choudhary",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );

        Contact result = service.updateContact(
                "personal",
                "Harsh",
                "Choudhary",
                updated
        );

        assertEquals("Anvii", result.getCity());
        assertEquals("9999999999", result.getPhoneNo());
    }
    
    @Test
    public void givenNonExistingContact_whenUpdate_shouldReturnNull() {

        AddressBookService service = new AddressBookService();

        Contact updated = new Contact();

        Contact result = service.updateContact(
                "personal",
                "Unknown",
                "Person",
                updated
        );

        assertNull(result);
    }
    
    @Test
    public void givenMissingAddressBook_whenUpdate_shouldReturnNull() {

        AddressBookService service = new AddressBookService();

        Contact updated = new Contact();

        Contact result = service.updateContact(
                "unknownBook",
                "Tarus",
                "Prabhat",
                updated
        );

        assertNull(result);
    }
    
    @Test
    public void givenExistingContact_whenDeleted_shouldReturnTrue() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
        		"Harsh",
                "Choudhary",
                "Bombay",
                "Ariana",
                "Geornite",
                "859834",
                "9474237809",
                "hkc@gmail.com"
        );

        service.addContact("personal", contact);

        boolean result = service.deleteContact(
                "personal",
                "Tarus",
                "Prabhat"
        );

        assertTrue(result);
    }
    
    @Test
    public void givenMissingContact_whenDelete_shouldReturnFalse() {

        AddressBookService service = new AddressBookService();

        boolean result = service.deleteContact(
                "personal",
                "Unknown",
                "Person"
        );

        assertFalse(result);
    }
    
    @Test
    public void givenMissingAddressBook_whenDelete_shouldReturnFalse() {

        AddressBookService service = new AddressBookService();

        boolean result = service.deleteContact(
                "unknownBook",
                "Tarus",
                "Prabhat"
        );

        assertFalse(result);
    }
}