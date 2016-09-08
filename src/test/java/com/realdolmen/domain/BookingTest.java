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

public class BookingTest extends JpaPersistenceTest {
	private static Validator validator;
	private static ValidatorFactory factory;
	
	private static Customer customer;
	private static Address address;
	private static Flight inbound;
	private static Flight outbound;
	
	@BeforeClass
	public static void setup() throws ParseException{
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
		
		inbound = new Flight();
		Location arrivalLocationInbound = new Location("Brussels Airport", "Belgium", "BRU", "Western Europe");
		Location departureLocationInbound = new Location("John F. Kennedy International Airport", "USA", "JFK", "North America");
		inbound.setArrivalLocation(arrivalLocationInbound);
		inbound.setDepartureLocation(departureLocationInbound);
		inbound.setBasePrice(50.0);
		Date departureTimeInbound = sdf.parse("2016-09-01");
		inbound.setDepartureTime(departureTimeInbound);
		
		
		outbound = new Flight();
		Location arrivalLocationOutbound = new Location("Brussels Airport", "Belgium", "BRU", "Western Europe");
		Location departureLocationOutbound = new Location("John F. Kennedy International Airport", "USA", "JFK", "North America");
		outbound.setArrivalLocation(arrivalLocationOutbound);
		outbound.setDepartureLocation(departureLocationOutbound);
		outbound.setBasePrice(50.0);
		Date departureTimeOutbound = sdf.parse("2016-09-01");
		outbound.setDepartureTime(departureTimeOutbound);
		
	}
	
	@Test
	public void bookingIsValid(){
		Booking booking = new Booking();
		booking.setCustomer(customer);
		booking.setInbound(inbound);
		booking.setOutbound(outbound);
		Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void bookingsIsInvalid(){
		Booking booking = new Booking();
		booking.setCustomer(customer);
		booking.setInbound(inbound);
		Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
		assertEquals(1, violations.size());
	}
}
