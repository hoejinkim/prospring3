package com.prospring3.ch14.jsr303;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.prospring3.ch14.domain.Customer;
import com.prospring3.ch14.domain.CustomerType;
import com.prospring3.ch14.jsr303.service.MyBeanValidationService;

public class Jsr303Sample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:jsr303-app-context.xml");
		context.refresh();

		MyBeanValidationService myBeanValidationService = context.getBean(
				"myBeanValidationService", MyBeanValidationService.class);

		Customer customer = new Customer();

		// 기본 제약 조건 테스트
		customer.setFirstName("C");
		customer.setLastName("Ho");
		customer.setCustomerType(null);
		customer.setGender(null);
		
		// 커스텀 제약 조건 테스트
		customer.setFirstName("Clarence");
		customer.setLastName("Ho");
		customer.setCustomerType(CustomerType.INDIVIDUAL);
		customer.setGender(null);

		validateCustomer(customer, myBeanValidationService);
	}

	private static void validateCustomer(Customer customer,
			MyBeanValidationService myBeanValidationService) {
		Set<ConstraintViolation<Customer>> violations = new HashSet<ConstraintViolation<Customer>>();
		violations = myBeanValidationService.validateCustomer(customer);

		listViolations(violations);
	}

	private static void listViolations(
			Set<ConstraintViolation<Customer>> violations) {
		System.out.println("No. of violations: " + violations.size());

		for (ConstraintViolation<Customer> violation : violations) {
			System.out.println("Validation error for property: "
					+ violation.getPropertyPath() + " with value: "
					+ violation.getInvalidValue() + " with error message: "
					+ violation.getMessage());
		}
	}

}
