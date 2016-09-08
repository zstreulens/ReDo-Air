package com.realdolmen.web.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Location;
import com.realdolmen.service.FlightServiceBean;
import com.realdolmen.service.LocationServiceBean;

@Named
@SessionScoped
public class FlightBean implements Serializable {
	List<Flight> allFlights;
	List<Flight> outboundFlights;
	List<Flight> inboundFlights;
	List<String> countries;
	List<String> airports;
	Flight newFlight;
	Location departureLocation;
	Location arrivalLocation;

	private String cabinClass;
	private String airline;
	boolean oneWay;

	String fromLocation;
	String toLocation;
	String fromAirport;
	String toAirport;
	String message;
	private Date currentDate;
	private Date departureDate;
	private Date returnDate;

	String page;

	private boolean rendered;

	@Inject
	FlightServiceBean flightService;
	@Inject
	LocationServiceBean locationService;

	@PostConstruct
	public void setUp() {
		allFlights = flightService.findFlights();
		countries = locationService.findCountries();
		airports = locationService.findAirports();
		outboundFlights = null;
		page = "search";
		oneWay = true;
		currentDate = Calendar.getInstance().getTime();
		newFlight = new Flight();
	}

	public FlightBean() {
	}

	public String addFlight() {
		message = null;
		try {
			departureLocation = locationService.findLocationByAirport(fromAirport);
			arrivalLocation = locationService.findLocationByAirport(toAirport);
			newFlight.setDepartureLocation(departureLocation);
			newFlight.setArrivalLocation(arrivalLocation);
			flightService.createFlight(newFlight);
			clean();
			message = "Flight added succesfully.";
			return "flightAdded";
		} catch (Exception e) {
			message = "Something went wrong.";
			e.printStackTrace();
			return "failure";
		}

	}

	public void clean() {
		newFlight = new Flight();
		fromLocation = null;
		toLocation = null;
		departureLocation = null;
		arrivalLocation = null;
		outboundFlights = null;
		inboundFlights = null;
		departureDate = null;
		returnDate = null;
		fromAirport = null;
		toAirport = null;
		allFlights = flightService.findFlights();
	}

	public void submitAction() {
		outboundFlights = flightService.findFlightFromQuery(fromLocation, toLocation, departureDate, returnDate);
		inboundFlights = flightService.findFlightFromQuery(toLocation, fromLocation, departureDate, returnDate);
		setPage("outbound");
	}

	public void reset() {
		setRendered(false);
		RequestContext.getCurrentInstance().reset("form");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public String getToAirport() {
		return toAirport;
	}

	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}

	public List<String> getAirports() {
		return airports;
	}

	public void setAirports(List<String> airports) {
		this.airports = airports;
	}

	public Location getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Location departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Location getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(Location arrivalLocation) {
		arrivalLocation = arrivalLocation;
	}

	public Flight getNewFlight() {
		return newFlight;
	}

	public void setNewFlight(Flight newFlight) {
		this.newFlight = newFlight;
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

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public boolean isOneWay() {
		return oneWay;
	}

	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getCurrentDate() {
		return currentDate;
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

	public List<Flight> findAllFlights() {
		return flightService.findFlights();
	}

	public String search() {
		return "searchResults";
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

}
