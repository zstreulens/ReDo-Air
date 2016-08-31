package com.realdolmen.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class FlightBean implements Serializable {
	
	List<Flight> flights;

	
	@Inject
	FlightServiceBean flightService;
	
	
	@PostConstruct
	public void setUp() {
		flights = flightService.findFlights();
	}
	private Flight flight = new Flight();

	public FlightBean() {
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightServiceBean getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightServiceBean flightService) {
		this.flightService = flightService;
	}
	
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight() {
		flightService.createFlight(flight);
	}
	
	public List<Flight> findAllFlights() {
		return flightService.findFlights();
	}
	
	public String search() {
		return "searchResults";
	}
	
	
	
}
