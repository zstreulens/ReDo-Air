package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Customer;
import com.realdolmen.utilities.JpaPersistenceTest;

public class CustomerRepositoryTest extends JpaPersistenceTest {
	private CustomerRepository customerRepository;
	private Customer customer;
	private Customer customer2;

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
		customer.setMailAddress("ziggy@test.be");
		customer.setPassword("test");

		customer2 = new Customer();
		Address address2 = new Address();
		address2.setStreet("Prinsenpad");
		address2.setNumber("134");
		address2.setCity("Rijkevorsel");
		address2.setZipcode("2310");
		address2.setCountry("Belgium");
		customer2.setAddress(address2);
		customer2.setFirstName("Cato");
		customer2.setLastName("Streulens");
		customer2.setMailAddress("cato@test.be");
		customer2.setPassword("test");
	}

	@Test
	public void shouldSaveCustomer() {
		customerRepository.createCustomer(customer);
		customerRepository.createCustomer(customer2);
		assertNotNull(customer.getId());
		assertNotNull(customer2.getId());
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
		Customer foundCustomer = customerRepository.findByMail("ziggy@test.be");
		assertEquals("Ziggy", foundCustomer.getFirstName());
	}
}
