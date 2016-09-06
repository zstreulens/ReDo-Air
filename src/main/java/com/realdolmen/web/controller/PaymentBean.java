package com.realdolmen.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Creditcard;
import com.realdolmen.domain.CreditcardType;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.BookingRepository;
import com.realdolmen.repository.CreditcardRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private Creditcard newCreditcard;
	private Booking booking;
	private Flight flight;
	private List<Creditcard> creditcards;
	private String creditcardType;
	@Inject
	CustomerBean customerBean;
	@Inject
	CustomerRepository customerRepository;
	@Inject
	BookingRepository bookingRepository;
	@Inject
	CreditcardRepository creditcardRepository;
	@Inject
	FlightServiceBean flightService;
	@Inject
	OverviewBean overviewBean;
	Customer loggedInCustomer;
	
	@PostConstruct
	public void init(){
		newCreditcard = new Creditcard();
		getTypes();
	}
	
	public CreditcardType[] getTypes(){
		return CreditcardType.values();
	}

	public String pay() {
		loggedInCustomer = customerBean.getLoggedInCustomer();
		creditcards = loggedInCustomer.getCreditcard();
		if (newCreditcard != null) {
			newCreditcard.setCustomer(loggedInCustomer);
			creditcardRepository.createCreditcard(newCreditcard);
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

	public Creditcard getNewCreditcard() {
		return newCreditcard;
	}

	public void setNewCreditcard(Creditcard newCreditcard) {
		this.newCreditcard = newCreditcard;
	}
}