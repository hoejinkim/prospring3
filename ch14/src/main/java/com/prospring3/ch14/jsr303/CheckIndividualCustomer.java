package com.prospring3.ch14.jsr303;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.prospring3.ch14.jsr303.validator.IndividualCustomerValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IndividualCustomerValidator.class)
@Documented
public @interface CheckIndividualCustomer {

	String message() default "Individual customer should have gender and last name defined";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
