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
	Date departMinusDays;
	Date departPlusDays;
	
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

	
	
	
	
	public List<Flight> findFlightWithParams(String departCountry, String departAirport, String arriveCountry, String arriveAirport, Date departureDate) {
		
		if (departureDate == null) {
			departureDate = new Date();
			departMinusDays = addDays(departureDate, -1);
			departPlusDays = addDays(departureDate, 30);
		} else {
			departMinusDays = addDays(departureDate, -1);
			departPlusDays = addDays(departureDate, 1);
		}
		
		if ((departAirport == null || departAirport.isEmpty()) && (arriveAirport == null || arriveAirport.isEmpty())) {
			return findFlightByCountries(departCountry, arriveCountry, departureDate);
		} else {
			return findFlightByAirport(departAirport, arriveAirport, departureDate);
		}
	
	}
	
	private List<Flight> findFlightByAirport(String departAirport, String arriveAirport, Date departureDate) {
		
	return em
			.createQuery(
					"SELECT f FROM Flight f WHERE f.departureLocation.name = :departAirport AND f.arrivalLocation.name = :arriveAirport AND (f.departureTime BETWEEN :departMinusDays AND :departPlusDays))",
					Flight.class)
			.setParameter("arriveAirport", arriveAirport).setParameter("departAirport", departAirport)
			.setParameter("departMinusDays", departMinusDays, TemporalType.TIMESTAMP)
			.setParameter("departPlusDays", departPlusDays, TemporalType.TIMESTAMP)
			.getResultList();
}
	
	
	private List<Flight> findFlightByCountries(String departCountry, String arriveCountry, Date departureDate) {
		
	return em
			.createQuery(
					"SELECT f FROM Flight f WHERE f.departureLocation.country = :departCountry AND f.arrivalLocation.country = :arriveCountry AND (f.departureTime BETWEEN :departMinusDays AND :departPlusDays))",
					Flight.class)
			.setParameter("arriveCountry", arriveCountry).setParameter("departCountry", departCountry)
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
