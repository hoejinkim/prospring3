package com.prospring3.ch14.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.prospring3.ch14.domain.Contact;

@Component("contactValidator")
public class ContactValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Contact.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
	}

}
