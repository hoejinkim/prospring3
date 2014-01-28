package com.apress.prospring3.ch10.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch10.domain.ContactTelDetail;
import com.apress.prospring3.ch10.repository.ContactTelDetailRepository;
import com.apress.prospring3.ch10.service.ContactTelDetailService;
import com.google.common.collect.Lists;

@Service("contactTelDetailService")
@Repository
@Transactional
public class ContactTelDetailServiceImpl implements ContactTelDetailService {
	
	@Autowired
	private ContactTelDetailRepository contactTelDetailRepository;

	public List<ContactTelDetail> findAll() {
		return Lists.newArrayList(contactTelDetailRepository.findAll());
	}

}
