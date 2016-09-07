package com.realdolmen.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Creditcard;
import com.realdolmen.domain.CreditcardType;
import com.realdolmen.domain.Customer;
import com.realdolmen.utilities.JpaPersistenceTest;

public class CreditcardRepositoryTest extends JpaPersistenceTest {
	private CreditcardRepository creditcardRepository;
	private CustomerRepository customerRepository;
	private Creditcard creditcard;
	private Customer customer;
	private Address address;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
	
	@Before
	public void initializeRepository() throws ParseException{
		creditcardRepository = new CreditcardRepository();
		creditcardRepository.entityManager = entityManager();
		customerRepository = new CustomerRepository();
		customerRepository.entityManager = entityManager();
		
		customer = new Customer();
		address = new Address();
		
		address.setStreet("Prinsenpad");
		address.setNumber("134");
		address.setCity("Rijkevorsel");
		address.setZipcode("2310");
		address.setCountry("Belgium");
		
		customer.setAddress(address);
		customer.setFirstName("Ziggy");
		customer.setLastName("Streulens");
		customer.setMailAddress("ziggy@test.be");
		customer.setPassword("test");
		customerRepository.createCustomer(customer);
		
		creditcard = new Creditcard();
		creditcard.setNumber("12345678912345");
		creditcard.setControlnumber("9876");
		creditcard.setCreditcardType(CreditcardType.VISA);
		creditcard.setExpiryDate((Date) sdf.parse("12/17"));
		creditcard.setCustomer(customer);
	}
	
	@Test
	public void shouldSaveCreditcard(){
		creditcardRepository.createCreditcard(creditcard);
		assertSame(creditcard, creditcardRepository.findById(creditcard.getId()));
	}
	
	@Test
	public void shouldUpdateCreditcard(){
		creditcardRepository.createCreditcard(creditcard);
		creditcard.setNumber("98765432195324");
		creditcardRepository.updateCreditcard(creditcard);
		assertSame(creditcard, creditcardRepository.findById(creditcard.getId()));
	}
}
