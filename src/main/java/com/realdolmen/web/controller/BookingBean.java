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

	public String bookFlight(Flight flight) throws IOException {
		setFlightToBook(flight);
		if (customerbean.getLoggedInCustomer() != null) {
			return "success";
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
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
