package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Location;

@Stateless
public class FlightRepository {
	@PersistenceContext
	EntityManager em;

	public Flight save(Flight flight) {
		em.persist(flight);
		return flight;
	}

	public List<Flight> findAll() {
		return em.createQuery("select f from Flight f", Flight.class).getResultList();
	}
	
	public List<Flight> findFlightWithParams() {
		return em.createNamedQuery("findFlight",Flight.class).getResultList();
	}


}
