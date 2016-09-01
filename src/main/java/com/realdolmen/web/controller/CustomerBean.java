package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.BCrypt;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Customer;
import com.realdolmen.repository.CustomerRepository;

@Named
@SessionScoped
public class CustomerBean implements Serializable {
	@Inject
	CustomerRepository customerRepository;
	private Customer customer;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@PostConstruct
	public void init() {
		customer = new Customer();
		customer.setAddress(new Address());
	}

	public void register() {
		try {
			customerRepository.createCustomer(customer);
			cleanCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {
		try {
			customer = customerRepository.findByMail(customer.getMailAddress());
			if (BCrypt.checkpw(password, customer.getPassword())) {
				System.out.println("It matches!");
			} else {
				System.out.println("It doesn't match =(");
			}
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
	}

	public void cleanCustomer() {
		customer = null;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
