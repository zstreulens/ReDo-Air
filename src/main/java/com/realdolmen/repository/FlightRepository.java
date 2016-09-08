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

	public List<Flight> findFlightWithParams(String departId, String arriveId, Date departureDate, Date returnDate) {
		Date departMinusOneDay;
		Date departPlusOneDay;

		if (departureDate == null) {
			departureDate = new Date();
			departMinusOneDay = addDays(departureDate, -1);
			departPlusOneDay = addDays(departureDate, 30);
		} else {
			departMinusOneDay = addDays(departureDate, -1);
			departPlusOneDay = addDays(departureDate, 1);
		}

		return em
				.createQuery(
						"SELECT f FROM Flight f WHERE f.departureLocation.country = :departLoc AND f.arrivalLocation.country = :arrivalLoc AND (f.departureTime BETWEEN :departMinusOneDay AND :departPlusOneDay))",
						Flight.class)
				.setParameter("arrivalLoc", arriveId).setParameter("departLoc", departId)
				.setParameter("departMinusOneDay", departMinusOneDay, TemporalType.TIMESTAMP)
				.setParameter("departPlusOneDay", departPlusOneDay, TemporalType.TIMESTAMP).getResultList();
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
