package com.prospring3.ch14.convserv;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.prospring3.ch14.domain.Contact;

public class ConvServExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:conv-service-app-context.xml");
		context.refresh();
		
		Contact clarence = context.getBean("clarence", Contact.class);
		System.out.println(clarence);
	}
	
}
