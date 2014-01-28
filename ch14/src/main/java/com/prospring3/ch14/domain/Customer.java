package com.prospring3.ch14.domain;

import javax.validation.constraints.*;

import com.prospring3.ch14.jsr303.CheckIndividualCustomer;

//@CheckIndividualCustomer
public class Customer {

	@NotNull
	@Size(min = 2, max = 60)
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private CustomerType customerType;
	
	private Gender gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public boolean isIndividualCustomer() {
		return this.customerType.equals(CustomerType.INDIVIDUAL);
	}
	
	@AssertTrue(message = "Individaul customer should have gender and last name defined")
	private boolean isValidIndividualCustomer() {
		boolean result = true;
		
		if (getCustomerType() != null
				&& (isIndividualCustomer() && (gender == null || lastName == null))) {
			result = false;
		}
		
		return result;
	}
	
}
