package com.addressbookapp.util;

import com.addressbookapp.model.Contact;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class AddressBookJSONIO {

    private final Gson gson;

    public AddressBookJSONIO() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void writeContactsToJSON(String fileName, List<Contact> contactList) {
        try(FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(contactList, writer);
            System.out.println("Contacts written to JSON successfully.");
        }catch(IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    public void readContactsFromJSON(String fileName) {
        try(FileReader reader = new FileReader(fileName)) {
            Type contactListType = new TypeToken<List<Contact>>() {}.getType();
            List<Contact> contacts = gson.fromJson(reader, contactListType);

            System.out.println("\nContacts from JSON file:");
            if(contacts != null && !contacts.isEmpty()) {
                for(Contact contact : contacts) {
                    System.out.println(contact);
                }
            }else {
                System.out.println("No contacts found in JSON file.");
            }
        }catch(Exception e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(localDate.toString());
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString());
        }
    }
}