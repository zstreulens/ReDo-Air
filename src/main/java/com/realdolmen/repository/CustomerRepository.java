package com.realdolmen.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Customer;

@Stateless
public class CustomerRepository {
	@PersistenceContext
	EntityManager entityManager;

	public Customer createCustomer(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		entityManager.merge(customer);
		return customer;
	}

	public Customer findById(Integer id) {
		return entityManager.find(Customer.class, id);
	}
	
	public Customer findByMail(String mail){
		return entityManager.createNamedQuery("Customer.findByMail", Customer.class).setParameter("mailAddress", mail).getSingleResult();
	}
}
