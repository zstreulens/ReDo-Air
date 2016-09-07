package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Flight;

@Stateless
public class FlightRepository {
	@PersistenceContext
	EntityManager em;

	public Flight save(Flight flight) {
		em.persist(flight);
		return flight;
	}

	public Flight findById(Long id) {
		return em.find(Flight.class, id);
	}

	public List<Flight> findAll() {
		return em.createQuery("select f from Flight f", Flight.class).getResultList();
	}

	public List<Flight> findFlightWithParams(String departId, String arriveId) {
		return em.createNamedQuery("findFlight", Flight.class).setParameter("arrivalLoc", arriveId)
				.setParameter("departLoc", departId).getResultList();
	}
	
	public Flight update(Flight flight){
		em.merge(flight);
		return flight;
	}

}
