package com.addressbookapp.util;

import com.addressbookapp.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookJSONIO {

    public void writeContactsToJSON(String fileName, List<Contact> contactList) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(fileName)) {

            gson.toJson(contactList, writer);

            System.out.println("Contacts written to JSON successfully.");

        }catch(IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }
    
    public void readContactsFromJSON(String fileName) {

        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fileName)) {

            Type contactListType = new TypeToken<List<Contact>>() {}.getType();

            List<Contact> contacts = gson.fromJson(reader, contactListType);

            System.out.println("\nContacts from JSON file:");

            if(contacts != null) {
                for(Contact contact : contacts) {
                    System.out.println(contact);
                }
            }

        }catch(Exception e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }
}