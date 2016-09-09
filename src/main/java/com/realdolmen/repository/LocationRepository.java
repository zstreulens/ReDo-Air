package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Location;

@Stateless
public class LocationRepository {

	@PersistenceContext
	EntityManager em;

	public Location save(Location location) {
		em.persist(location);
		return location;
	}

	public List<Location> findAll() {
		return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
	}

	public List<String> findCountries() {
		return em.createQuery("SELECT DISTINCT l.country FROM Location l", String.class).getResultList();
	}

	public List<String> findAirports() {
		return em.createQuery("SELECT DISTINCT l.name FROM Location l", String.class).getResultList();
	}

	public Location findByAirport(String airport) {
		return em.createNamedQuery("Location.findByCountry", Location.class).setParameter("name", airport)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<String> findAirportsByCountry(String country) {
		return em.createQuery("SELECT l.name FROM Location l WHERE l.country = :country")
				.setParameter("country", country).getResultList();
	}

}