package com.realdolmen.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Flight;
@Remote
public interface FlightRemoteInterface {

	List<Flight> findFlights();
	Flight createFlight(Flight flight);
	public Flight findById(Long id);
	List<Flight> findFlightFromQuery(String departId, String arriveId, Date departureDate, Date returnDate);

}

