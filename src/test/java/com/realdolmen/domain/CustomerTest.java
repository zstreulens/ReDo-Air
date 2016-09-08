package com.realdolmen.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;


public class CustomerTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void customerIsValid(){
		Customer customer = new Customer();
		Address address = new Address();
		address.setStreet("Veldstraat");
		address.setNumber("43/001");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		customer.setAddress(address);
		customer.setFirstName("Ziggy");
		customer.setLastName("Streulens");
		customer.setMailAddress("ziggy.streulens@realdolmen.com");
		customer.setPassword("test");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		Assert.assertEquals(0, violations.size());
	}
	
	@Test
	public void customerIsInvalid(){
		Customer customer = new Customer();
		Address address = new Address();
		address.setStreet("Veldstraat");
		address.setNumber("43/001");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		customer.setAddress(address);
		customer.setFirstName("Ziggy");
		customer.setLastName("");
		customer.setMailAddress("ziggy.streulens@realdolmen.com");
		customer.setPassword("test");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		Assert.assertEquals(1, violations.size());
	}
}
