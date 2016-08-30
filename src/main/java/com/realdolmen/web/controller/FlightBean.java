package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class FlightBean implements Serializable {
	
	@Inject
	FlightServiceBean flightService;
	
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
	
	
	
}