package com.realdolmen.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class CompanyTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void companyIsValid(){
		Company company = new Company();
		company.setName("Brussels Airlines");
		company.setPassword("test");
		Set<ConstraintViolation<Company>> violations = validator.validate(company);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void companyIsInvalid(){
		Company company = new Company();
		company.setName("Brussels Airlines");
		Set<ConstraintViolation<Company>> violations = validator.validate(company);
		assertEquals(1, violations.size());
	}
}
