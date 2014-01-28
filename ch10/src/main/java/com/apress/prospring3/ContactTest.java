package com.apress.prospring3;

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
	}
	
	private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}
	
}
