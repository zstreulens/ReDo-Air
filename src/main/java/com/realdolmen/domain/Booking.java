package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Booking.findBookingsCustomer", query = "SELECT b FROM Booking b WHERE b.customer.id = :customerId")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@NotNull
	private Customer customer;
	@ManyToOne
	private Flight inbound;
	@ManyToOne
	@NotNull
	private Flight outbound;

	public Booking() {

	}

	public Booking(Customer customer, Flight outbound) {
		this.customer = customer;
		this.outbound = outbound;
	}

	public Booking(Customer customer, Flight outbound, Flight inbound) {
		this.customer = customer;
		this.inbound = inbound;
		this.outbound = outbound;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Flight getInbound() {
		return inbound;
	}

	public void setInbound(Flight inbound) {
		this.inbound = inbound;
	}

	public Flight getOutbound() {
		return outbound;
	}

	public void setOutbound(Flight outbound) {
		this.outbound = outbound;
	}
}
