package com.realdolmen.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Location;
import com.realdolmen.utilities.JpaPersistenceTest;

public class FlightRepositoryTest extends JpaPersistenceTest {
	private FlightRepository flightRepository;
	private LocationRepository locationRepository;
	private Flight flight;
	private Flight newFlight;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void initializeRepository() throws ParseException{
		flightRepository = new FlightRepository();
		flightRepository.em = entityManager();
		locationRepository = new LocationRepository();
		locationRepository.em = entityManager();
		newFlight = new Flight();
		Location arrivalLocation = locationRepository.findByAirport("John F. Kennedy International Airport");
		Location departureLocation = locationRepository.findByAirport("Brussels Airport");
		newFlight.setArrivalLocation(arrivalLocation);
		newFlight.setDepartureLocation(departureLocation);
		newFlight.setBasePrice(50.0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date departureTime = sdf.parse("2016-09-01");
		newFlight.setDepartureTime(departureTime);
	}
	
	@Test
	public void shouldSaveFlight(){
		flightRepository.save(newFlight);
		assertNotNull(newFlight.getId());
	}
	
	@Test
	public void shouldFindAllFlights(){
		List<Flight> flights =  flightRepository.findAll();
		assertEquals(226, flights.size());
	}
	
	@Test
	public void shouldFindFlightById(){
		Flight flight = flightRepository.findById(1L);
		assertEquals("1", flight.getId().toString());
	}
	
	@Test
	public void shouldFindFlightByParams() throws ParseException{
		Date departureDate = sdf.parse("2016-09-27");
		List<Flight> flights = flightRepository.findFlightWithParams("London Heathrow Airport", "John F. Kennedy International Airport", departureDate);
		assertEquals(1, flights.size());
	}
	
	@Test
	public void shouldUpdateFlight(){
		flight = flightRepository.findById(2L);
		flight.setBasePrice(800.0);
		assertSame(flight, flightRepository.findById(2L));
	}
}
