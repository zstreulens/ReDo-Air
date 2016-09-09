package com.realdolmen.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Creditcard implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Size(min = 14, max = 16)
	private String number;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date expiryDate;
	private String controlnumber;
	@Enumerated(EnumType.STRING)
	private CreditcardType creditcardType;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@NotNull
	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
