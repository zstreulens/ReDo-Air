package com.realdolmen.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.BCrypt;

@Entity
@NamedQuery(name = "Customer.findByMail", query = "SELECT c FROM Customer c WHERE c.mailAddress = :mailAddress")
public class Customer implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Embedded
	@Valid
	private Address address;
	@NotBlank
	private String mailAddress;
	@NotBlank
	private String password;
	private String creditCard;

	public Customer() {

	}

	public Customer(String firstName, String lastName, Address address, String mailAddress, String password,
			String creditCard) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mailAddress = mailAddress;
		this.password = password;
		this.creditCard = creditCard;
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

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

}
