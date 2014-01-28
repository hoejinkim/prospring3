package com.prospring3.ch14.convserv;

import java.util.*;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

import com.prospring3.ch14.domain.AnotherContact;
import com.prospring3.ch14.domain.Contact;

public class ConvServExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:conv-service-app-context.xml");
		context.refresh();
		
		Contact clarence = context.getBean("clarence", Contact.class);
		System.out.println(clarence);
		
		ConversionService conversionService = context.getBean(ConversionService.class);
		
		// Contact에서 AnotherContact로 변환
		AnotherContact anotherContact = conversionService.convert(clarence, AnotherContact.class);
		System.out.println(anotherContact);
		
		// String에서 Array로 변환
		String[] stringArray = conversionService.convert("a, b, c", String[].class);
		System.out.println("String array: " + stringArray[0] + stringArray[1] + stringArray[2]);
		
		// List에서 Set으로 변환
		List<String> listString = new ArrayList<String>();
		listString.add("a");
		listString.add("b");
		listString.add("c");
		Set<String> setString = conversionService.convert(listString, HashSet.class);
		for (String string : setString) {
			System.out.println("Set: " + string);
		}
	}
	
}
