package com.realdolmen.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Flight;

@Named
@SessionScoped
public class BookingBean implements Serializable {

	private Flight outboundFlight;
	private Flight inboundFlight;
	@Inject
	CustomerBean customerbean;
	@Inject
	PaymentBean paymentBean;
	@Inject
	FlightBean flightBean;

	public Flight getOutboundFlight() {
		return outboundFlight;
	}

	public void setOutboundFlight(Flight outboundFlight) {
		this.outboundFlight = outboundFlight;
	}

	public Flight getInboundFlight() {
		return inboundFlight;
	}

	public void setInboundFlight(Flight inboundFlight) {
		this.inboundFlight = inboundFlight;
	}

	public void bookOutboundFlight(SelectEvent event) {
		Flight flight = (Flight)event.getObject();
		setOutboundFlight(flight);
			flightBean.setPage("inbound");
	}
		
		public void bookInboundFlight(SelectEvent event) {
			Flight flight = (Flight)event.getObject();
			setInboundFlight(flight);
			if (customerbean.getLoggedInCustomer() != null) {
				paymentBean.findFlight(inboundFlight.getId());
				flightBean.setPage("payment");
			} else {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	
	
}
