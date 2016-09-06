package com.realdolmen.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Creditcard implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String number;
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	private String controlnumber;
	@Enumerated(EnumType.STRING)
	private CreditcardType creditcardType;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	public String getControlnumber() {
		return controlnumber;
	}

	public void setControlnumber(String controlnumber) {
		this.controlnumber = controlnumber;
	}

	public CreditcardType getCreditcardType() {
		return creditcardType;
	}

	public void setCreditcardType(CreditcardType creditcardType) {
		this.creditcardType = creditcardType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
