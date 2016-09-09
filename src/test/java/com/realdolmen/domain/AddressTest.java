package com.realdolmen.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class AddressTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void addressIsValid(){
		Address address = new Address();
		address.setStreet("Veldstraat");
		address.setNumber("43");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void addressIsInvalid(){
		Address address = new Address();
		address.setStreet("Veldstraat");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(1, violations.size());
	}
}
