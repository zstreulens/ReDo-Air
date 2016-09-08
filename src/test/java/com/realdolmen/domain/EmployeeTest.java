package com.realdolmen.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class EmployeeTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void isValidEmployee(){
		Employee employee = new Employee();
		employee.setMailAddress("ziggy@test.be");
		employee.setPassword("test");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void isInvalidEmployee(){
		Employee employee = new Employee();
		employee.setMailAddress("t@d.be");
		employee.setPassword("test");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertEquals(1, violations.size());
	}
}
