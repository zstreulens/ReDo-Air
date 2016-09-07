package com.realdolmen.repository;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Flight;
import com.realdolmen.utilities.JpaPersistenceTest;

public class FlightRepositoryTest extends JpaPersistenceTest {
	private FlightRepository flightRepository;
	private Flight flight;
	
	@Before
	public void initializeRepository(){
		flightRepository = new FlightRepository();
		flightRepository.em = entityManager();
	}
	
	@Test
	public void shouldFindAllFlights(){
		List<Flight> flights =  flightRepository.findAll();
		assertEquals(14, flights.size());
	}
	
	@Test
	public void shouldFindFlightById(){
		Flight flight = flightRepository.findById(1L);
		assertEquals("1", flight.getId().toString());
	}
	
	@Test
	public void shouldFindFlightByParams(){
		List<Flight> flights = flightRepository.findFlightWithParams("Belgium", "Spain");
		assertEquals(1, flights.size());
	}
}
