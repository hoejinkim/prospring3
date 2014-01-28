package com.apress.prospring3.ch10.service.jpa;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch10.domain.*;
import com.apress.prospring3.ch10.repository.ContactRepository;
import com.apress.prospring3.ch10.service.ContactService;
import com.google.common.collect.Lists;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	@Transactional(readOnly = true)
	public List<Contact> findByFirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		Contact contact = contactRepository.findOne(id);
		for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
			System.out.println(contactTelDetail);
		}
		for (Hobby hobby : contact.getHobbies()) {
			System.out.println(hobby);
		}
		return contact;
	}

}
