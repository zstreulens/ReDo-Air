package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.Customer;

@Remote
public interface CustomerRemoteInterface {
	Customer createCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	Customer findById(Integer id);

	Customer findByMail(String mail);
}
