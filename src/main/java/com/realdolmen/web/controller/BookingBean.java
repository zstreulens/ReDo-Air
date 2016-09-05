package com.realdolmen.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Flight;

@Named
@SessionScoped
public class BookingBean implements Serializable {

	private Flight flightToBook;
	@Inject
	CustomerBean customerbean;
	@Inject
	PaymentBean paymentBean;

	public String bookFlight(Flight flight) {
		setFlightToBook(flight);
		if (customerbean.getLoggedInCustomer() != null) {
			paymentBean.findFlight(flightToBook.getId());
			return "success";
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "failure";
		}
	}

	public Flight getFlightToBook() {
		return flightToBook;
	}

	public void setFlightToBook(Flight flightToBook) {
		this.flightToBook = flightToBook;
	}
}
