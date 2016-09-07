package com.realdolmen.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Employee.findByMail", query = "SELECT e FROM Employee e WHERE e.mailAddress = :mailAddress")
public class Employee implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String mailAddress;
	private String password;

	
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
		this.password = password;
	}
}
