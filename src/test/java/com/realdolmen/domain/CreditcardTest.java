package com.realdolmen.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class CreditcardTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	private static Customer customer;
	private static Address address;
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");

	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		customer = new Customer();
		address = new Address();
		
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
		
	}
	
	@Test
	public void creditcardIsValid() throws ParseException{
		Creditcard creditcard = new Creditcard();
		creditcard.setCustomer(customer);
		creditcard.setNumber("123456789123458");
		creditcard.setControlnumber("1234");
		creditcard.setCreditcardType(CreditcardType.VISA);
		Date expiryDate = sdf.parse("12/17");
		creditcard.setExpiryDate(expiryDate);
		Set<ConstraintViolation<Creditcard>> violations = validator.validate(creditcard);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void creditcardIsInvalid() {
		Creditcard creditcard = new Creditcard();
		creditcard.setCustomer(customer);
		creditcard.setNumber("123456789");
		creditcard.setControlnumber("3124");
		creditcard.setCreditcardType(CreditcardType.AMEX);
		Set<ConstraintViolation<Creditcard>> violations = validator.validate(creditcard);
		assertEquals(2, violations.size());
	}
}
