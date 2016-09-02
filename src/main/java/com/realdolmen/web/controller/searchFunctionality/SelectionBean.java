package com.realdolmen.web.controller.searchFunctionality;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.realdolmen.domain.Flight;

@Named
@ViewScoped
public class SelectionBean implements Serializable {
	
	long id;


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
			FacesContext.getCurrentInstance().getExternalContext().redirect("detail.jsf?id=" + ((Flight)event.getObject()).getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




