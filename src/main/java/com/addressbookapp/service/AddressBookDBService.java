package com.addressbookapp.service;

import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AddressBookDBService {

    public List<Contact> getAllContactsFromDB() {
        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT id, first_name, last_name, address, city, state, zip, phone_number, email, date_added FROM contacts";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Contact contact = mapResultSetToContact(resultSet);
                contactList.add(contact);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching contacts from DB: " + e.getMessage());
        }

        return contactList;
    }

    public Contact getContactByFirstName(String firstName) {
        String query = "SELECT id, first_name, last_name, address, city, state, zip, phone_number, email, date_added " +
                       "FROM contacts WHERE first_name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToContact(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching contact by name: " + e.getMessage());
        }

        return null;
    }

    public boolean updateContactCityByName(String firstName, String newCity) {
        String query = "UPDATE contacts SET city = ? WHERE first_name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newCity);
            preparedStatement.setString(2, firstName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error while updating contact city: " + e.getMessage());
        }

        return false;
    }

    public boolean isContactSyncedWithDB(Contact memoryContact) {
        if (memoryContact == null) {
            return false;
        }

        Contact dbContact = getContactByFirstName(memoryContact.getFirstName());
        return dbContact != null && memoryContact.equals(dbContact);
    }

    public List<Contact> getContactsAddedBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT id, first_name, last_name, address, city, state, zip, phone_number, email, date_added " +
                       "FROM contacts WHERE date_added BETWEEN ? AND ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Contact contact = mapResultSetToContact(resultSet);
                    contactList.add(contact);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching contacts by date range: " + e.getMessage());
        }

        return contactList;
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

        java.sql.Date sqlDate = resultSet.getDate("date_added");
        if (sqlDate != null) {
            contact.setDateAdded(sqlDate.toLocalDate());
        }

        return contact;
    }
    
    public Map<String, Integer> getContactCountByCity() {

        Map<String, Integer> cityCountMap = new HashMap<>();

        String query = "SELECT city, COUNT(*) AS total_contacts FROM contacts GROUP BY city";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()) {
                String city = resultSet.getString("city");
                int count = resultSet.getInt("total_contacts");

                cityCountMap.put(city, count);
            }

        }catch(SQLException e) {
            System.out.println("Error retrieving count by city: " + e.getMessage());
        }

        return cityCountMap;
    }
    
    public Map<String, Integer> getContactCountByState() {

        Map<String, Integer> stateCountMap = new HashMap<>();

        String query = "SELECT state, COUNT(*) AS total_contacts FROM contacts GROUP BY state";

        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()) {
                String state = resultSet.getString("state");
                int count = resultSet.getInt("total_contacts");

                stateCountMap.put(state, count);
            }

        }catch(SQLException e) {
            System.out.println("Error retrieving count by state: " + e.getMessage());
        }

        return stateCountMap;
    }
    
    public boolean addContactToDatabase(int addressBookId, Contact contact) {

        String query = "INSERT INTO contacts(first_name,last_name,address,city,state,zip,phone_number,email,date_added,address_book_id) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getAddress());
            ps.setString(4, contact.getCity());
            ps.setString(5, contact.getState());
            ps.setString(6, contact.getZip());
            ps.setString(7, contact.getPhoneNumber());
            ps.setString(8, contact.getEmail());
            ps.setDate(9, java.sql.Date.valueOf(contact.getDateAdded()));
            ps.setInt(10, addressBookId);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting contact: " + e.getMessage());
            return false;
        }
    }
    
    // Add Multiple Contacts to database
    public void addMultipleContactsToDatabase(String addressBookName, List<Contact> contacts) {

        int addressBookId = ensureAddressBookExists(addressBookName);

        List<Thread> threadList = new ArrayList<>();

        for(Contact contact : contacts) {

            Thread thread = new Thread(() -> {

                System.out.println("Thread started for: " + contact.getFirstName());

                boolean isAdded = addContactToDatabase(addressBookId, contact);

                if(isAdded) {
                    System.out.println("Contact inserted successfully: " + contact.getFirstName());
                }else{
                    System.out.println("Failed to insert contact: " + contact.getFirstName());
                }

            });

            threadList.add(thread);
            thread.start();
        }

        for(Thread thread : threadList) {
            try{
                thread.join();
            }catch(InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("All contacts inserted using threads.");
    }
    
    // Checks if database exists or not?
    public int ensureAddressBookExists(String addressBookName) {

        String selectQuery = "SELECT id FROM address_books WHERE name = ?";
        String insertQuery = "INSERT INTO address_books(name) VALUES(?)";

        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setString(1, addressBookName);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, addressBookName);
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error ensuring address book: " + e.getMessage());
        }

        return -1;
    }
}