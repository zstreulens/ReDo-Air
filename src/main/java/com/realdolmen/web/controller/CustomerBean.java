package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Customer;
import com.realdolmen.repository.CustomerRepository;

@Named
@SessionScoped
public class CustomerBean implements Serializable {
	@Inject
	CustomerRepository customerRepository;
	private Customer customer;
	
	@PostConstruct
	public void init(){
		customer = new Customer();
		customer.setAddress(new Address());
	}

	public void register() {
		try {
			customerRepository.createCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
