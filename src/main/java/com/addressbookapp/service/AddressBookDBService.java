package com.addressbookapp.service;

import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    public List<Contact> getAllContactsFromDB() {
        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT id, first_name, last_name, address, city, state, zip, phone_number, email FROM contacts";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setAddress(resultSet.getString("address"));
                contact.setCity(resultSet.getString("city"));
                contact.setState(resultSet.getString("state"));
                contact.setZip(resultSet.getString("zip"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setEmail(resultSet.getString("email"));

                contactList.add(contact);
            }

        }catch(SQLException e) {
            System.out.println("Error while fetching contacts from DB: " + e.getMessage());
        }

        return contactList;
    }
}