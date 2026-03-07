package com.addressbookapp.model;

import java.util.Objects;
import java.time.LocalDate;
import java.util.Objects;

public class Contact {
	private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;
    
    private LocalDate date_added;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String address,
                   String city, String state, String zip,
                   String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public LocalDate getDateAdded() { return date_added; }
    public void setDateAdded(LocalDate dateAdded) { this.date_added = dateAdded; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Contact)) return false;

        Contact other = (Contact) obj;

        return id == other.id
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(address, other.address)
                && Objects.equals(city, other.city)
                && Objects.equals(state, other.state)
                && Objects.equals(zip, other.zip)
                && Objects.equals(phoneNumber, other.phoneNumber)
                && Objects.equals(email, other.email)
                && Objects.equals(date_added, other.date_added);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, city, state, zip, phoneNumber, email);
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName +
                " | Address: " + address +
                " | City: " + city +
                " | State: " + state +
                " | Zip: " + zip +
                " | Phone: " + phoneNumber +
                " | Email: " + email +
                " | dateAdded: " + (date_added != null ? date_added : "N/A");
    }
}