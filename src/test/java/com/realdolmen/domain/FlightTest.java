package com.realdolmen.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class FlightTest {
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
		String departureTimeString = sdf.format("2016-09-01");
		Date departureTime = sdf.parse(departureTimeString);
		flight.setDepartureTime(departureTime);
	}
	
	@Test 
	public void flightIsInvalid(){
		
	}
}
