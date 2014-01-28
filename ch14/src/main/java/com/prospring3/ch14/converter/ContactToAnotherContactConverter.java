package com.prospring3.ch14.converter;

import org.springframework.core.convert.converter.Converter;

import com.prospring3.ch14.domain.AnotherContact;
import com.prospring3.ch14.domain.Contact;

public class ContactToAnotherContactConverter implements Converter<Contact, AnotherContact> {

	@Override
	public AnotherContact convert(Contact contact) {
		AnotherContact anotherContact = new AnotherContact();
		anotherContact.setFirstName(contact.getLastName());
		anotherContact.setLastName(contact.getFirstName());
		anotherContact.setBirthDate(contact.getBirthDate());
		anotherContact.setPersonalSite(contact.getPersonalSite());
		
		return anotherContact;
	}

}
