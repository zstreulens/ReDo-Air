package com.realdolmen.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.realdolmen.domain.Flight;
import com.realdolmen.repository.LocationRepository;
import com.realdolmen.service.FlightServiceBean;
import com.realdolmen.service.LocationServiceBean;

@Named
@SessionScoped
public class FlightBean implements Serializable {

	List<Flight> allFlights;
	List<Flight> outboundFlights;
	List<Flight> inboundFlights;
	List<String> countries;

	String fromLocation;
	String toLocation;

	String page = "search";

	private boolean rendered;

	@Inject
	FlightServiceBean flightService;
	@Inject
	LocationRepository locationRepository;
	@Inject
	LocationServiceBean locationService;

	@PostConstruct
	public void setUp() {
		allFlights = flightService.findFlights();
		outboundFlights = null;
	}

	private Flight flight = new Flight();

	public FlightBean() {
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

	public List<Flight> getAllFlights() {
		return allFlights;
	}

	public void setAllFlights(List<Flight> allFlights) {
		this.allFlights = allFlights;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public List<Flight> getOutboundFlights() {
		return outboundFlights;
	}

	public void setOutboundFlights(List<Flight> outboundFlights) {
		this.outboundFlights = outboundFlights;
	}

	public List<Flight> getInboundFlights() {
		return inboundFlights;
	}

	public void setInboundFlights(List<Flight> inboundFlights) {
		this.inboundFlights = inboundFlights;
	}

	public void addFlight() {
		flightService.createFlight(flight);
	}

	public List<Flight> findAllFlights() {
		return flightService.findFlights();
	}

	public String search() {
		return "searchResults";
	}

	public void findFlightFromQuery() {
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public void submitAction() {
		outboundFlights = flightService.findFlightFromQuery(fromLocation, toLocation);
		inboundFlights = flightService.findFlightFromQuery(toLocation, fromLocation);
		setPage("outbound");
	}

	public void reset() {
		setRendered(false);
		RequestContext.getCurrentInstance().reset("form");
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}
}
