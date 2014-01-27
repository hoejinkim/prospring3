package com.prospring3.ch14.pe;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.prospring3.ch14.domain.Contact;

public class PropEditorExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:prop-editor-app-context.xml");
		context.refresh();
		
		Contact clarence = context.getBean("clarence", Contact.class);
		System.out.println(clarence);
		
		Contact myContact = context.getBean("myContact", Contact.class);
		System.out.println(myContact);
	}
	
}
