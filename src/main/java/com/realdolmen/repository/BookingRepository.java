package com.realdolmen.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Booking;

@Stateless
public class BookingRepository {
	@PersistenceContext
	EntityManager entityManager;

	public Booking createBooking(Booking booking) {
		entityManager.persist(booking);
		return booking;
	}

	public Booking updateBooking(Booking booking) {
		entityManager.merge(booking);
		return booking;
	}

	public Booking findById(Integer id) {
		return entityManager.find(Booking.class, id);
	}

	public void deleteBooking(Integer id) {
		entityManager.remove(entityManager.find(Booking.class, id));
	}

	public List<Booking> findBookingsCustomer(Integer customerId) {
		return entityManager.createNamedQuery("Booking.findBookingsCustomer", Booking.class)
				.setParameter("customerId", customerId).getResultList();
	}
}
