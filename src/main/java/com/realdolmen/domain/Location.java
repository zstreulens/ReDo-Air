package com.realdolmen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Location {
	@Id @GeneratedValue
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String country;
	@NotNull
	private Integer code;
	@NotBlank
	private String globalRegion;
	
	
	
	public Location() {
		
	}
	public Location(String name, String country, Integer code, String globalRegion) {
		this.name = name;
		this.country = country;
		this.code = code;
		this.globalRegion = globalRegion;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getGlobalRegion() {
		return globalRegion;
	}
	public void setGlobalRegion(String globalRegion) {
		this.globalRegion = globalRegion;
	}
}
