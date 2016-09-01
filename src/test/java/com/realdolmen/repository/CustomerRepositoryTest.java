package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Customer;
import com.realdolmen.utilities.JpaPersistenceTest;

public class CustomerRepositoryTest extends JpaPersistenceTest {
	private CustomerRepository customerRepository;
	private Customer customer;

	@Before
	public void initializeRepository() {
		customerRepository = new CustomerRepository();
		customerRepository.entityManager = entityManager();
		customer = new Customer();
		Address address = new Address();
		address.setStreet("Veldstraat");
		address.setNumber("43/001");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		customer.setAddress(address);
		customer.setFirstName("Ziggy");
		customer.setLastName("Streulens");
		customer.setMailAddress("ziggy.streulens@realdolmen.com");
		customer.setPassword("test");
	}

	@Test
	public void shouldSaveCustomer() {
		customerRepository.createCustomer(customer);
		assertNotNull(customer.getId());
	}

	@Test
	public void shouldUpdateCustomer() {
		customerRepository.createCustomer(customer);
		customer.setFirstName("Joske");
		customerRepository.updateCustomer(customer);
		Customer foundCustomer = customerRepository.findById(customer.getId());
		assertEquals("Joske", foundCustomer.getFirstName());
	}

	@Test
	public void shouldFindCustomerById() {
		customerRepository.createCustomer(customer);
		Customer foundCustomer = customerRepository.findById(1);
		assertNotNull(foundCustomer);
		assertNotNull(foundCustomer.getId());
		assertEquals("Ziggy", foundCustomer.getFirstName());
	}

	@Test
	public void shouldFindCustomerByMail() {
		customerRepository.createCustomer(customer);
		Customer foundCustomer = customerRepository.findByMail("ziggy.streulens@realdolmen.com");
		assertEquals("Ziggy", foundCustomer.getFirstName());
	}
}
