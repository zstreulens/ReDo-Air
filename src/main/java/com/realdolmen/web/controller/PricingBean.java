package com.realdolmen.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class PricingBean implements Serializable {
	List<Flight> flights;
	private Flight flightPricing;

	@Inject
	FlightServiceBean flightService;
	@Inject
	EmployeeBean employeeBean;

	@PostConstruct
	public void setUp() {
		updateFlights();
	}
	
	public void updateFlights(){
		flights = flightService.findFlights();
	}

	public void detailFlight(SelectEvent event) {
		if (event.getObject() != null) {
			Flight flight = (Flight) event.getObject();
			setFlightPricing(flight);
		}

		if (employeeBean.getLoggedInEmployee() != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("detailPricing.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pricing.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String updatePrice() {
		if (flightPricing.getRedoPrice() == 0 || flightPricing.getRedoPrice() == null) {
			flightPricing.setRedoPrice(flightPricing.getBasePrice() * 0.05 + flightPricing.getBasePrice());
		}
		flightService.updateFlight(flightPricing);
		return "pricing.xhtml";
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight getFlightPricing() {
		return flightPricing;
	}

	public void setFlightPricing(Flight flightPricing) {
		this.flightPricing = flightPricing;
	}

}
