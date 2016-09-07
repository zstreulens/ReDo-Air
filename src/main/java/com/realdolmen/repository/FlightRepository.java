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
		Date departPlus = null;
		Date returnPlus = null;
		
		if (departureDate == null) {
			departureDate = new Date();
			departPlus = addDays(departureDate, 200);
		} else {
			departPlus = addDays(departureDate, 2);
		}

		if (returnDate == null) {
			returnDate = new Date();
			returnPlus = addDays(returnDate, 200);
		} else {
			returnPlus = addDays(returnDate, 2);
		}

		return em.createQuery(
				"SELECT f FROM Flight f WHERE f.arrivalLocation.country = :arrivalLoc AND f.departureLocation.country = :departLoc AND (f.arrivalTime BETWEEN :departDate AND :departPlus) AND (f.departureTime BETWEEN :returnDate AND :returnPlus)",
				Flight.class)
				.setParameter("arrivalLoc", (arriveId != null ? arriveId : "" ))
				.setParameter("departLoc", (departId != null ? departId : "" ))
				.setParameter("departDate", departureDate, TemporalType.TIMESTAMP)
				.setParameter("departPlus", departPlus, TemporalType.TIMESTAMP)
				.setParameter("returnDate", returnDate, TemporalType.TIMESTAMP)
				.setParameter("returnPlus", returnPlus, TemporalType.TIMESTAMP)
				.getResultList();
	}
	
	private Date addDays(Date originalDate, int addedDays) {
		Calendar c = Calendar.getInstance();
		c.setTime(originalDate);
		c.add(Calendar.DAY_OF_MONTH, addedDays);
		return c.getTime();
	}

}
