package com.realdolmen.web.controller.searchFunctionality;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.realdolmen.domain.Flight;
import com.realdolmen.service.FlightServiceBean;

@Named
@ViewScoped
public class SelectionView implements Serializable {

	private Flight cars1;
	private Flight cars2;
	private Flight selectedCar;
	private List<Flight> selectedCars;

	@Inject
	private FlightServiceBean service;

	@PostConstruct
	public void init() {
		cars1 = service.createFlight(new Flight());
		cars2 = service.createFlight(new Flight());
	}


	public Flight getCars1() {
		return cars1;
	}


	public void setCars1(Flight cars1) {
		this.cars1 = cars1;
	}


	public Flight getCars2() {
		return cars2;
	}


	public void setCars2(Flight cars2) {
		this.cars2 = cars2;
	}


	public Flight getSelectedCar() {
		return selectedCar;
	}


	public void setSelectedCar(Flight selectedCar) {
		this.selectedCar = selectedCar;
	}


	public List<Flight> getSelectedCars() {
		return selectedCars;
	}


	public void setSelectedCars(List<Flight> selectedCars) {
		this.selectedCars = selectedCars;
	}


	public FlightServiceBean getService() {
		return service;
	}


	public void setService(FlightServiceBean service) {
		this.service = service;
	}


	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}