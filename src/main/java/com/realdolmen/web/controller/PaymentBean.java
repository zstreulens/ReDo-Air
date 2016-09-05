package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.BookingRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private String newCreditcard;
	private Booking booking;
	private Flight flight;
	@Inject
	CustomerBean customerBean;
	@Inject
	CustomerRepository customerRepository;
	@Inject
	BookingRepository bookingRepository;
	@Inject
	FlightServiceBean flightService;
	@Inject
	OverviewBean overviewBean;
	Customer loggedInCustomer;

	public String pay() {
		loggedInCustomer = customerBean.getLoggedInCustomer();
		if (newCreditcard != "") {
			loggedInCustomer.setCreditcard(newCreditcard);
			customerRepository.updateCustomer(loggedInCustomer);
		}
		booking = new Booking(loggedInCustomer, flight);
		try {
			bookingRepository.createBooking(booking);
			overviewBean.findBookingsForCustomer(loggedInCustomer.getId());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	public void findFlight(long id) {
		flight = flightService.findById(id);
	}

	public String getNewCreditcard() {
		return newCreditcard;
	}

	public void setNewCreditcard(String newCreditcard) {
		this.newCreditcard = newCreditcard;
	}
}