package com.realdolmen.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Customer;
import com.realdolmen.repository.CustomerRepository;

@Stateless
@LocalBean
public class CustomerServiceBean implements CustomerRemoteInterface {
	
	@Inject
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer){
		return customerRepository.createCustomer(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer customer){
		return customerRepository.updateCustomer(customer);
	}
	
	@Override
	public Customer findById(Integer id){
		return customerRepository.findById(id);
	}
	
	@Override
	public Customer findByMail(String mail){
		return customerRepository.findByMail(mail);
	}
}
