package com.prospring3.ch14.jsr303.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.prospring3.ch14.domain.Customer;
import com.prospring3.ch14.jsr303.CheckIndividualCustomer;

public class IndividualCustomerValidator implements
		ConstraintValidator<CheckIndividualCustomer, Customer> {

	public void initialize(CheckIndividualCustomer checkIndividualCustomer) {
	}

	public boolean isValid(Customer customer, ConstraintValidatorContext context) {
		boolean result = true;
		
		if (customer.getCustomerType() != null
				&& (customer.isIndividualCustomer() 
						&& (customer.getLastName() == null 
						|| customer.getGender() == null))) {
			result = false;
		}
		
		return result;
	}
}
