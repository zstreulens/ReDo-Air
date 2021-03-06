package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.BCrypt;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Customer;
import com.realdolmen.service.CustomerServiceBean;

@Named
@SessionScoped
public class CustomerBean implements Serializable {
	@Inject
	CustomerServiceBean customerService;
	@Inject
	BookingBean bookingBean;
	private Customer customer;
	private String password;
	private Customer loggedInCustomer;
	private String loginErrorMessage;
	private String registerErrorMessage;

	@PostConstruct
	public void init() {
		customer = new Customer();
		customer.setAddress(new Address());
		loggedInCustomer = null;
	}

	public String register() {
		try {
			if (customerService.findByMail(customer.getMailAddress()) == null) {
				customerService.createCustomer(customer);
				cleanCustomer();
				registerErrorMessage = "";
				loginErrorMessage = "";
				return "success";
			} else {
				registerErrorMessage = "E-mail already exists.";
				return "failure";
			}
		} catch (Exception e) {
			registerErrorMessage = "Something went wrong.";
			e.printStackTrace();
			return "failure";
		}
	}

	public String login() {
		try {
			customer = customerService.findByMail(customer.getMailAddress());
			if (BCrypt.checkpw(password, customer.getPassword())) {
				loggedInCustomer = customer;
				loginErrorMessage = "";
				registerErrorMessage = "";
				if (bookingBean.getOutboundFlight() != null) {
					return "flights";
				} else {
					return "success";
				}
			} else {
				loginErrorMessage = "Password is incorrect.";
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginErrorMessage = "E-mail is incorrect.";
			return "failure";
		}
	}

	public String logout() {
		cleanCustomer();
		return "flights";
	}

	public void cleanCustomer() {
		customer = new Customer();
		customer.setAddress(new Address());
		loggedInCustomer = null;
	}

	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}

	public void setLoginErrorMessage(String loginErrorMessage) {
		this.loginErrorMessage = loginErrorMessage;
	}

	public String getRegisterErrorMessage() {
		return registerErrorMessage;
	}

	public void setRegisterErrorMessage(String registerErrorMessage) {
		this.registerErrorMessage = registerErrorMessage;
	}

	@Produces
	@Named("user")
	public Customer getLoggedInCustomer() {
		return loggedInCustomer;
	}

	public void setLoggedInCustomer(Customer loggedInCustomer) {
		this.loggedInCustomer = loggedInCustomer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String goToLogin() {
		return "login";
	}

	public String goToRegistration() {
		cleanCustomer();
		return "register";
	}
}
