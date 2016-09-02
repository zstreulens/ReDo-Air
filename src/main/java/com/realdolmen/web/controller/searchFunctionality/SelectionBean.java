package com.realdolmen.web.controller.searchFunctionality;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Flight;
import com.realdolmen.web.controller.FlightDetailsBean;

@Named
@ViewScoped
public class SelectionBean implements Serializable {

	long id;
	@Inject
	FlightDetailsBean flightdetails;

	public SelectionBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void onRowSelect(SelectEvent event) {
		try {
			flightdetails.findFlight(((Flight) event.getObject()).getId());
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("detail.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
