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
                Contact contact = mapResultSetToContact(resultSet);
                contactList.add(contact);
            }

        }catch(SQLException e) {
            System.out.println("Error while fetching contacts from DB: " + e.getMessage());
        }

        return contactList;
    }

    public Contact getContactByFirstName(String firstName) {
        String query = "SELECT id, first_name, last_name, address, city, state, zip, phone_number, email " +
                "FROM contacts WHERE first_name = ?";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    return mapResultSetToContact(resultSet);
                }
            }

        }catch(SQLException e) {
            System.out.println("Error while fetching contact by name: " + e.getMessage());
        }

        return null;
    }

    public boolean updateContactCityByName(String firstName, String newCity) {
        String query = "UPDATE contacts SET city = ? WHERE first_name = ?";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newCity);
            preparedStatement.setString(2, firstName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(SQLException e) {
            System.out.println("Error while updating contact city: " + e.getMessage());
        }

        return false;
    }

    public boolean updateContactPhoneByName(String firstName, String newPhoneNumber) {
        String query = "UPDATE contacts SET phone_number = ? WHERE first_name = ?";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPhoneNumber);
            preparedStatement.setString(2, firstName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(SQLException e) {
            System.out.println("Error while updating contact phone number: " + e.getMessage());
        }

        return false;
    }

    public boolean isContactSyncedWithDB(Contact memoryContact) {
        Contact dbContact = getContactByFirstName(memoryContact.getFirstName());
        return memoryContact != null && memoryContact.equals(dbContact);
    }

    private Contact mapResultSetToContact(ResultSet resultSet) throws SQLException {
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
        return contact;
    }
}