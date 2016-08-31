package com.realdolmen.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Location;
import com.realdolmen.repository.FlightRepository;

@Stateless
@LocalBean
public class FlightServiceBean implements FlightRemoteInterface{

	@Inject
	FlightRepository flightRepository;
	
	@Override
	public List<Flight> findFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight createFlight(Flight flight) {
		return flightRepository.save(flight);
	}

}
