package com.addressbookapp.util;

import com.addressbookapp.model.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookCSVIO {

    public void writeContactsToCSV(String fileName, List<Contact> contactList) {

        try(CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {

            // Header
            String[] header = {"firstName","lastName","address","city","state","zip","phoneNumber","email"};
            writer.writeNext(header);

            for(Contact contact : contactList) {

                String[] data = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getCity(),
                        contact.getState(),
                        contact.getZip(),
                        contact.getPhoneNumber(),
                        contact.getEmail()
                };

                writer.writeNext(data);
            }

            System.out.println("Contacts written to CSV successfully.");

        }catch(IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
    
    public void readContactsFromCSV(String fileName) {

        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {

            String[] line;

            System.out.println("\nContacts from CSV file:");

            while((line = reader.readNext()) != null) {

                for(String value : line) {
                    System.out.print(value + " | ");
                }

                System.out.println();
            }

        } catch(Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }
}