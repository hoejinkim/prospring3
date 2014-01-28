package com.prospring3.ch14.convserv;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

import com.prospring3.ch14.domain.Contact;

public class ConvFormatServExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:conv-format-service-app-context.xml");
		context.refresh();
		
		Contact clarence = context.getBean("clarence", Contact.class);
		
		System.out.println("Contact info: " + clarence);
		
		ConversionService conversionService = context.getBean("conversionService", ConversionService.class);
		System.out.println("Birthdate of contact is: " + conversionService.convert(clarence.getBirthDate(), String.class));
	}
	
}
