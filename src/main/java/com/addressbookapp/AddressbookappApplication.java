package com.addressbookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressbookapp.console.AddressBookMain;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBook;

@SpringBootApplication
public class AddressbookappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressbookappApplication.class, args);

// You can temporarily test(feature/uc-02-add-contact-console) it inside AddressBookApplication:
//
//		Contact contact = new Contact(
//		        "devarshi",
//		        "mishra",
//		        "ABC Street",
//		        "Indore",
//		        "MP",
//		        "452001",
//		        "9999999999",
//		        "devarshi@gmail.com"
//		);
//
//		System.out.println(contact);
		
	}
}