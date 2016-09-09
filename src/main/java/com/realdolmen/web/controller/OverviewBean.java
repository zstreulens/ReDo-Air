package com.realdolmen.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Booking;
import com.realdolmen.service.BookingServiceBean;

@Named
@SessionScoped
public class OverviewBean implements Serializable {
	private List<Booking> bookings;
	@Inject
	BookingServiceBean bookingService;
	@Inject
	CustomerBean customerbean;

	@PostConstruct
	public void setup() {
		bookings = new ArrayList<>();
		bookings = bookingService.findBookingsCustomer(customerbean.getLoggedInCustomer().getId());
	}

	public void findBookingsForCustomer(Integer customerId) {
		bookings = bookingService.findBookingsCustomer(customerId);
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
