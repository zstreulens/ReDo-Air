package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mindrot.BCrypt;

@Entity
@NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	@NotNull
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hashed;
	}

}
