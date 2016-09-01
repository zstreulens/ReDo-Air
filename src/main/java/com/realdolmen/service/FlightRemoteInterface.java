package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Flight;
@Remote
public interface FlightRemoteInterface {

	List<Flight> findFlights();
	Flight createFlight(Flight flight);
	List<Flight> findFlightFromQuery(Long departId, Long arriveId);
}

