package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Booking;

@Remote
public interface BookingRemoteInterface {
	Booking createBooking(Booking booking);

	Booking updateBooking(Booking booking);

	Booking findById(Integer id);

	void deleteBooking(Integer id);

	List<Booking> findBookingsCustomer(Integer customerId);
}
