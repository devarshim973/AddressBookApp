package com.addressbookapp.util;

import com.addressbookapp.model.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookFileIO {

    public void writeContactsToFile(String fileName, List<Contact> contactList) {
        try(FileWriter writer = new FileWriter(fileName)) {
            for(Contact contact : contactList) {
                writer.write(contact.toString());
                writer.write(System.lineSeparator());
            }
            System.out.println("Contacts written to file successfully.");
        }catch(IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

    public void readContactsFromFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            System.out.println("\nContacts from file:");
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
    }
}