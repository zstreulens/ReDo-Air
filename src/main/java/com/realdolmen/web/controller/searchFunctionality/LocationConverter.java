package com.realdolmen.web.controller.searchFunctionality;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.realdolmen.domain.Location;
import com.realdolmen.service.LocationServiceBean;

@FacesConverter("locationConverter")
	public class LocationConverter implements Converter {
	 
	    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	        if(value != null && value.trim().length() > 0) {
	            try {
	                LocationServiceBean service = (LocationServiceBean) fc.getExternalContext().getApplicationMap().get("themeService");
	                return service.findLocations().get(Integer.parseInt(value));
	            } catch(NumberFormatException e) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid location."));
	            }
	        }
	        else {
	            return null;
	        }
	    }
	 
	    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
	        if(object != null) {
	            return String.valueOf(((String) object));
	        }
	        else {
	            return null;
	        }
	    }   
	}     