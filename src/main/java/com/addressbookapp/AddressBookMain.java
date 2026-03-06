package com.addressbookapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.addressbookapp.model.Contacts;

public class AddressBookMain {
    private static List<Contacts> contacts = new ArrayList<>();
    
    // Add Contacts
    public static void add(String s) {
    	String[] arr = s.split(":");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	contacts.add(new Contacts(arr[0],
    			arr[1],arr[2],arr[3],arr[4],
    			Integer.parseInt(arr[5]), arr[6], arr[7]));
    }
    
    // Update Contacts
    public static void update(String name ,String s) {
    	String[] arr = s.split(":");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	for(Contacts c: contacts) {
    		if((name).equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
    			c.setFirstName(arr[0]);
    			c.setLastName(arr[1]);
    			c.setAddress(arr[2]);
    			c.setCity(arr[3]);
    			c.setState(arr[4]);
    			c.setZip(Integer.parseInt(arr[5]));
    			c.setPhoneNo(arr[6]);
    			c.setEmail(arr[7]);
    			return;
    		}
    	}
    	System.out.println("User Not Found ");
    }
    
    // Delete Contacts
    public static void delete(String name) {
    	for(Contacts c: contacts) {
    		if(name.equalsIgnoreCase(c.getFirstName()+" "+c.getLastName())) {
    			System.out.println("Deleted contact : "+c.toString());
    			contacts.remove(c);
    			return;
    		}
    	}
    	System.out.println("User Not Found");
    }
    
    public static void main(String[] args ) throws IOException{
      
    	add("Harshal:Choudhary:Burhanpur:Burhanpur:450331:9795289383:harsh2005@gmail.com");
    	add("Ayush:Mishra:Katni:Katni:MP:464022:6858434244:ayushmishra3424@gmail.com");
    	add("Devarshi:Mishra:Satna:Satna:MP:14531:7566343644:mishradevarshi39@gmail.com");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	update("Ayush Mishra","Ayush:Mishra:Katni:Katni:MP:464022:6858434244:ayushmishra3424@gmail.com");
    	System.out.println("\n");
    	
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	
    	System.out.println("\n");
    	delete("Devarshi Mishra");
    	
    	System.out.println("\n");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    }
    
}