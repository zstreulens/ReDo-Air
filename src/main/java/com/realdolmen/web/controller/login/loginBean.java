package com.realdolmen.web.controller.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class loginBean implements Serializable {
	String userType;

	public loginBean() {
	}
	
	@PostConstruct
	public void init() {
		userType = "customer";;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String register() {
		return "register";
	}
	
	
}
