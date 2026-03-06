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
    	if(arr.length != 8) {
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	contacts.add(new Contacts(arr[0], arr[1], arr[2], arr[3], arr[4],
    			Integer.parseInt(arr[5]), arr[6], arr[7]));
    }
    
    public static void main(String[] args ) throws IOException{
    	BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    	
    	String line;
    	while((line=read.readLine())!=null) {
    		add(line);
    	}
    	for(Contacts c : contacts) {
    		System.out.println(c.toString());
    	}
    }
    
}