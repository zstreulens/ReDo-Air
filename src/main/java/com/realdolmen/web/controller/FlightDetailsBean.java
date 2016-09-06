package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@ApplicationScoped
public class FlightDetailsBean implements Serializable {

	Flight flight;

	@Inject
	FlightServiceBean flightService;
	@Inject
	BookingBean bookingBean;

	public void findFlight(long id) {
		flight = flightService.findById(id);
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
