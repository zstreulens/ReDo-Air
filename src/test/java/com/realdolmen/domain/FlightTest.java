package com.realdolmen.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.utilities.JpaPersistenceTest;

public class FlightTest extends JpaPersistenceTest{
	private static Validator validator;
	private static ValidatorFactory factory;
	
	@BeforeClass
	public static void setup(){
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void flightIsValid() throws ParseException{
		Flight flight = new Flight();
		Location arrivalLocation = new Location("Brussels Airport", "Belgium", "BRU", "Western Europe");
		Location departureLocation = new Location("John F. Kennedy International Airport", "USA", "JFK", "North America");
		flight.setArrivalLocation(arrivalLocation);
		flight.setDepartureLocation(departureLocation);
		flight.setPrice(50.0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date departureTime = sdf.parse("2016-09-01");
		flight.setDepartureTime(departureTime);
		Set<ConstraintViolation<Flight>> violations = validator.validate(flight);
		Assert.assertEquals(0, violations.size());
	}
	
	@Test 
	public void flightIsInvalid() throws ParseException{
		Flight flight = new Flight();
		Location arrivalLocation = new Location("Brussels Airport", "Belgium", "BRU", "Western Europe");
		flight.setArrivalLocation(arrivalLocation);
		flight.setPrice(50.0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date departureTime = sdf.parse("2016-09-01");
		flight.setDepartureTime(departureTime);
		Set<ConstraintViolation<Flight>> violations = validator.validate(flight);
		Assert.assertEquals(1, violations.size());
	}
}
