package com.realdolmen.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Booking;
import com.realdolmen.repository.BookingRepository;

@Stateless
@LocalBean
public class BookingServiceBean implements BookingRemoteInterface {
	@Inject
	BookingRepository bookingRepository;

	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.createBooking(booking);
	}

	@Override
	public Booking updateBooking(Booking booking) {
		return bookingRepository.updateBooking(booking);
	}

	@Override
	public Booking findById(Integer id) {
		return bookingRepository.findById(id);
	}

	@Override
	public void deleteBooking(Integer id) {
		bookingRepository.deleteBooking(id);
	}

	@Override
	public List<Booking> findBookingsCustomer(Integer customerId) {
		return bookingRepository.findBookingsCustomer(customerId);
	}
}
