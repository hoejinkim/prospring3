package com.apress.prospring3.ch10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.prospring3.ch10.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	public List<Contact> findByFirstName(String firstName);
	
	public List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
	
}
