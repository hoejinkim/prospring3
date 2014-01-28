package com.apress.prospring3;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch10.domain.ContactTelDetail;
import com.apress.prospring3.ch10.service.ContactTelDetailService;

public class ContactTelDetailTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:/META-INF/spring/app-context.xml");
		context.refresh();
		
		ContactTelDetailService contactTelDetailService = 
				context.getBean("contactTelDetailService", ContactTelDetailService.class);
		
		listContactTelDetails(contactTelDetailService.findAll());
	}
	
	private static void listContactTelDetails(List<ContactTelDetail> contactTelDetails) {
		for (ContactTelDetail contactTelDetail : contactTelDetails) {
			System.out.println(contactTelDetail);
		}
	}
}
