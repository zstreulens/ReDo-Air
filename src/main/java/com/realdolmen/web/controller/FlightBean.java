package com.realdolmen.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@SessionScoped
public class FlightBean implements Serializable {

	List<Flight> allFlights;
	List<Flight> filteredFlights;
	List<String> countries;

	String fromLocation;
	String toLocation;

	private boolean rendered;

	@Inject
	FlightServiceBean flightService;

	@PostConstruct
	public void setUp() {
		allFlights = flightService.findFlights();
		filteredFlights = null;
		rendered = false;
		countries = new ArrayList<>();
		countries.add("Belgium");
		countries.add("Spain");
		countries.add("China");
		countries.add("Japan");
		countries.add("USA");
		countries.add("UK");
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

	public List<Flight> getFilteredFlights() {
		return filteredFlights;
	}

	public void setFilteredFlights(List<Flight> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
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

	public void addFlight() {
		flightService.createFlight(flight);
	}

	public List<Flight> findAllFlights() {
		return flightService.findFlights();
	}

	public String search() {
		return "searchResults";
	}

	public void toggleRendered(boolean value) {
		setRendered(value);
	}

	public void toggleRendered() {
		setRendered(!isRendered());
	}

	public void findFlightFromQuery() {
		filteredFlights = flightService.findFlightFromQuery(fromLocation, toLocation);
	}

	public void searchAction(ActionEvent actionEvent) {
		findFlightFromQuery();
		toggleRendered();
	}

}
