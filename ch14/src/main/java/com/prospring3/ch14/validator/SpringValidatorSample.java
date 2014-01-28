package com.prospring3.ch14.validator;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.validation.*;

import com.prospring3.ch14.domain.Contact;

public class SpringValidatorSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:spring-validator-app-context.xml");
		context.refresh();
		
		Contact contact = new Contact();
		contact.setFirstName(null);
		contact.setLastName("Ho");
		
		Validator contactValidator = context.getBean("contactValidator", Validator.class);
		
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(contact, "Clarence");
		
		ValidationUtils.invokeValidator(contactValidator, contact, result);
		
		List<ObjectError> errors = result.getAllErrors();
		System.out.println("No of validation errors: " + errors.size());
		
		for (ObjectError error : errors) {
			System.out.println(error.getCode());
		}
	}
	
}
