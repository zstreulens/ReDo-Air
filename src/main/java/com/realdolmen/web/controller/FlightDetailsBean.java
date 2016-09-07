package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.BookingRepository;
import com.realdolmen.service.FlightServiceBean;

@Named
@ApplicationScoped
public class FlightDetailsBean implements Serializable {
	Flight flight;
	Booking booking;
	Customer loggedInCustomer;

	@Inject
	FlightServiceBean flightService;
	@Inject
	BookingBean bookingBean;
	@Inject
	CustomerBean customerBean;
	@Inject
	BookingRepository bookingRepository;
	@Inject
	OverviewBean overviewBean;
	
	@PostConstruct
	public void init() {
		loggedInCustomer = customerBean.getLoggedInCustomer();
	}

	public void findFlight(long id) {
		flight = flightService.findById(id);
	}

	public String bookFlight() {
		try {
			if (bookingBean.getInboundFlight() != null) {
				booking = new Booking(loggedInCustomer, bookingBean.getOutboundFlight(),
						bookingBean.getInboundFlight());
			} else {
				booking = new Booking(loggedInCustomer, bookingBean.getOutboundFlight());
			}
			bookingRepository.createBooking(booking);
			overviewBean.findBookingsForCustomer(loggedInCustomer.getId());
			bookingBean.setInboundFlight(null);
			bookingBean.setOutboundFlight(null);
			return "overview.xhtml"; 
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightServiceBean getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightServiceBean flightService) {
		this.flightService = flightService;
	}

}
