package com.apress.prospring3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch10.domain.Contact;
import com.apress.prospring3.ch10.service.ContactService;

public class ContactTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:/META-INF/spring/app-context.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("contactService", ContactService.class);
		
		listContacts(contactService.findAll());
		listContacts(contactService.findByFirstName("Clarence"));
		
		Contact contact = contactService.findById(1L);
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);
		listContacts(contacts);
	}
	
	private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}
	
}
