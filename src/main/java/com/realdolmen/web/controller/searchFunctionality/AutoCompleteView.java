package com.realdolmen.web.controller.searchFunctionality;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Location;
import com.realdolmen.service.LocationServiceBean;

@Named
public class AutoCompleteView {

	private String txt1;
	private Location location;

	@Inject
	private LocationServiceBean service;

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<String> completeLocation(String query) {
		List<String> allCountries = service.findCountries();
		List<String> filteredCountries = new ArrayList<String>();

		for (int i = 0; i < allCountries.size(); i++) {
			String country= allCountries.get(i);
			if (country.toLowerCase().startsWith(query)) {
				filteredCountries.add(country);
			}
		}

		return filteredCountries;
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Item Selected", event.getObject().toString()));
	}

}
