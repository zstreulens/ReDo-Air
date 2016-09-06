package com.realdolmen.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address implements Serializable {
	@Size (min = 1)
	private String street;
	@Size (min = 1)
	private String number;
	@Size (min = 1)
	private String city;
	@Size (min = 1)
	private String zipcode;
	@Size (min = 1)
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
