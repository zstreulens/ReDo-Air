package com.realdolmen.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.mindrot.BCrypt;

@Entity
@NamedQuery(name = "Customer.findByMail", query = "SELECT c FROM Customer c WHERE c.mailAddress = :mailAddress")
public class Customer implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	@Transient
	private String fullName;
	@Embedded
	@Valid
	private Address address;
	private String mailAddress;
	private String password;
	private String creditcard;
	@OneToMany(mappedBy="customer")
	private List<Booking> bookings;

	public Customer() {

	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Customer(String firstName, String lastName, Address address, String mailAddress, String password,
			String creditCard) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mailAddress = mailAddress;
		this.password = password;
		this.creditcard = creditCard;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hashed;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditCard) {
		this.creditcard = creditCard;
	}

}
