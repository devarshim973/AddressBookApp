package com.addressbookapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.addressbookapp.model.Contacts;

public class AddressBookMain {
    private static List<Contacts> contacts = new ArrayList<>();
    
    public static void add(String s) {
    	String[] arr = s.split(":");
    	if(arr.length!=8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	contacts.add(new Contacts(arr[0],
    			arr[1],arr[2],arr[3],arr[4],
    			Integer.parseInt(arr[5]), arr[6], arr[7]));
    }
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
    public static void main(String[] args ) throws IOException{
      
    	add("aman:pal:berkhera:bhopal:MP:12345:83056144536:aman936@gmail.com");
    	add("nikhil:kurmi:baisa:sagar:MP:462022:89564122121:nikhil@gmail.com");
    	add("amit:patel:maiyar:katni:MP:11111:7845129654:amit@gmail.com");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    	update("himesh kurmi","Himesh:kurmi:Anand Nager:Bhopal:MP:462022:89564122121:himeshkurmi@gmail.com ");
    	System.out.println("\n");
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    }
    
}