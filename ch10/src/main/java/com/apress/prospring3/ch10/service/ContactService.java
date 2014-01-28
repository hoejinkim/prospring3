package com.apress.prospring3.ch10.service;

import java.util.List;

import com.apress.prospring3.ch10.domain.Contact;

public interface ContactService {

	public List<Contact> findAll();
	
	public List<Contact> findByFirstName(String firstName);
	
}
