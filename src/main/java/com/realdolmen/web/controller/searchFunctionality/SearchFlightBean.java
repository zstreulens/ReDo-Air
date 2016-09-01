package com.realdolmen.web.controller.searchFunctionality;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SearchFlightBean implements Serializable {


	boolean oneWay;
	public SearchFlightBean() {
	}
	public SearchFlightBean(boolean oneWay) {
		this.oneWay = oneWay;
	}

	
	@PostConstruct
	public void setUp() {
		oneWay = true;
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
	
	
}
