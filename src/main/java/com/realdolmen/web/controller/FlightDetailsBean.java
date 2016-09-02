package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class FlightDetailsBean implements Serializable{

	Flight flight;
	
	@Inject
	FlightServiceBean flightService;
	
	public void findFlight(String idParam) {
		Long id = Long.parseLong(idParam);
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
