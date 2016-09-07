package com.realdolmen.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Flight;
import com.realdolmen.repository.FlightRepository;

@Stateless
@LocalBean
public class FlightServiceBean implements FlightRemoteInterface {

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

	@Override
	public List<Flight> findFlightFromQuery(String departId, String arriveId) {
		return flightRepository.findFlightWithParams(departId, arriveId);
	}

	public Flight findById(Long id) {
		return flightRepository.findById(id);
	}
}
