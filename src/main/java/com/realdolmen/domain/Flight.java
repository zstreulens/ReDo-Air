package com.realdolmen.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Flight implements Serializable {
	// FIELDS
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@ManyToOne
	private Location departureLocation;
	@NotNull
	@ManyToOne
	private Location arrivalLocation;
	@NotNull
	private Date departureTime;
	private Integer duration;
	private Integer seatsBusiness;
	private Integer seatsEconomy;
	private Integer seatsFirstClass;
	@NotNull
	private Double price;

	public Flight() {

	}

	public Flight(Location departureLocation, Location arrivalLocation, Date departureTime, Integer duration,
			Integer seatsBusiness, Integer seatsEconomy, Integer seatsFirstClass, Double price) {
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.duration = duration;
		this.seatsBusiness = seatsBusiness;
		this.seatsEconomy = seatsEconomy;
		this.seatsFirstClass = seatsFirstClass;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Location departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Location getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(Location arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getSeatsBusiness() {
		return seatsBusiness;
	}

	public void setSeatsBusiness(Integer seatsBusiness) {
		this.seatsBusiness = seatsBusiness;
	}

	public Integer getSeatsEconomy() {
		return seatsEconomy;
	}

	public void setSeatsEconomy(Integer seatsEconomy) {
		this.seatsEconomy = seatsEconomy;
	}

	public Integer getSeatsFirstClass() {
		return seatsFirstClass;
	}

	public void setSeatsFirstClass(Integer seatsFirstClass) {
		this.seatsFirstClass = seatsFirstClass;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
