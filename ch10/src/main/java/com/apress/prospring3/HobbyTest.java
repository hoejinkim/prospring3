package com.apress.prospring3;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch10.domain.Hobby;
import com.apress.prospring3.ch10.service.HobbyService;

public class HobbyTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:/META-INF/spring/app-context.xml");
		context.refresh();
		
		HobbyService hobbyService = context.getBean("hobbyService", HobbyService.class);
		
		listHobbies(hobbyService.findAll());
	}
	
	private static void listHobbies(List<Hobby> hobbies) {
		for (Hobby hobby : hobbies) {
			System.out.println(hobby);
		}
	}
	
}
