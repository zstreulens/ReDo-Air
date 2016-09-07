package com.realdolmen.web.controller.searchFunctionality;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SearchFlightBean implements Serializable {
	private String cabinClass;
	boolean oneWay;

	private Date currentDate = new Date();
	private Date departureDate;
	private Date returnDate;

	public SearchFlightBean() {
	}

	public SearchFlightBean(boolean oneWay) {
		this.oneWay = oneWay;
	}

	@PostConstruct
	public void setUp() {
		oneWay = true;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public boolean isOneWay() {
		return oneWay;
	}

	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}

	public void toggleOneWay(boolean oneWay) {
		setOneWay(oneWay);
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}