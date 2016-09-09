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
import com.realdolmen.service.CreditcardServiceBean;
import com.realdolmen.service.CustomerServiceBean;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private Creditcard newCreditcard;
	private Flight flight;
	private List<Creditcard> creditcards;
	private Creditcard creditcard;
	@Inject
	CustomerBean customerBean;
	@Inject
	CreditcardServiceBean creditcardService;
	@Inject
	FlightServiceBean flightService;
	@Inject
	OverviewBean overviewBean;
	Customer loggedInCustomer;

	@PostConstruct
	public void init() {
		newCreditcard = new Creditcard();
		creditcard = new Creditcard();
		getTypes();
		loggedInCustomer = customerBean.getLoggedInCustomer();
		creditcards = loggedInCustomer.getCreditcard();
	}

	public void addNewCreditcard() {
		System.out.println("Adding new creditcard");
		if (newCreditcard.getNumber() != "") {
			newCreditcard.setCustomer(loggedInCustomer);
			creditcardService.createCreditcard(newCreditcard);
		}
	}

	public CreditcardType[] getTypes() {
		return CreditcardType.values();
	}

	public Creditcard getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(Creditcard creditcard) {
		this.creditcard = creditcard;
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

	public List<Creditcard> getCreditcards() {
		return creditcards;
	}

	public void setCreditcards(List<Creditcard> creditcards) {
		this.creditcards = creditcards;
	}
}