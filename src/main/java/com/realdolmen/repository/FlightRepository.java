package com.realdolmen.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

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

	public List<Flight> findFlightWithParams(String departId, String arriveId, Date departureDate) {
		Date departMinusDays;
		Date departPlusDays;
		
		if (departureDate == null) {
			departureDate = new Date();
			departMinusDays = addDays(departureDate, -1);
			departPlusDays = addDays(departureDate, 30);
		} else {
			departMinusDays = addDays(departureDate, -1);
			departPlusDays = addDays(departureDate, 1);
		}
		
		

		return em
				.createQuery(
						"SELECT f FROM Flight f WHERE f.departureLocation.country = :departLoc AND f.arrivalLocation.country = :arrivalLoc AND (f.departureTime BETWEEN :departMinusDays AND :departPlusDays))",
						Flight.class)
				.setParameter("arrivalLoc", arriveId).setParameter("departLoc", departId)
				.setParameter("departMinusDays", departMinusDays, TemporalType.TIMESTAMP)
				.setParameter("departPlusDays", departPlusDays, TemporalType.TIMESTAMP)
				.getResultList();
	}

	private Date addDays(Date originalDate, int addedDays) {
		Calendar c = Calendar.getInstance();
		c.setTime(originalDate);
		c.add(Calendar.DAY_OF_MONTH, addedDays);
		return c.getTime();
	}

	public Flight update(Flight flight) {
		em.merge(flight);
		return flight;
	}

}
