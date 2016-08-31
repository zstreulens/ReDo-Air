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

public class LocationTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void locationIsVallid(){
		Location location = new Location();
		location.setName("Brussels Airport");
		location.setCountry("Belgium");
		location.setCode("BRU");
		location.setGlobalRegion("Western Europe");
		Set<ConstraintViolation<Location>> violations = validator.validate(location);
		Assert.assertEquals(0, violations.size());
	}
	
	@Test
	public void locationIsInvalid(){
		Location location = new Location();
		location.setName("Brussels Airport");
		location.setCode("BRU");
		location.setGlobalRegion("Western Europe");
		Set<ConstraintViolation<Location>> violations = validator.validate(location);
		Assert.assertEquals(1, violations.size());
	}
}
