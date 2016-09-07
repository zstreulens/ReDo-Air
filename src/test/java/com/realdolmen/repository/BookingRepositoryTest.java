package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Address;
import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Location;
import com.realdolmen.utilities.JpaPersistenceTest;

public class BookingRepositoryTest extends JpaPersistenceTest {
	private BookingRepository bookingRepository;
	private FlightRepository flightRepository;
	private CustomerRepository customerRepository;
	private Booking booking;
	private Customer customer;
	private Address address;
	private Flight outbound;
	private Flight inbound;
	
	@Before
	public void initializeRepository(){
		bookingRepository = new BookingRepository();
		flightRepository = new FlightRepository();
		customerRepository = new CustomerRepository();
		customerRepository.entityManager = entityManager();
		flightRepository.em = entityManager();
		bookingRepository.entityManager = entityManager();
		
		
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
		
		Flight outbound = flightRepository.findById(1L);
		
		booking = new Booking();
		booking.setCustomer(customer);
		booking.setOutbound(outbound);
		
		
	}
	
	@Test
	public void shouldSaveBooking(){
		bookingRepository.createBooking(booking);
		assertSame(booking, bookingRepository.findById(booking.getId()));
	}
	
	@Test
	public void shouldUpdateBooking(){
		bookingRepository.createBooking(booking);
		Flight inbound = flightRepository.findById(2L);
		booking.setInbound(inbound);
		bookingRepository.updateBooking(booking);
		assertSame(booking, bookingRepository.findById(booking.getId()));
	}
	
	@Test
	public void shouldFindBookingById(){
		bookingRepository.createBooking(booking);
		assertSame(booking, bookingRepository.findById(booking.getId()));
	}
	
	@Test
	public void shouldDeleteBooking(){
		bookingRepository.createBooking(booking);
		assertSame(booking, bookingRepository.findById(booking.getId()));
		bookingRepository.deleteBooking(booking.getId());
		assertNull(bookingRepository.findById(booking.getId()));
	}
	
	@Test
	public void shouldFindAllBookingsCustomer(){
		bookingRepository.createBooking(booking);
		assertEquals(1, bookingRepository.findBookingsCustomer(customer.getId()).size());
	}
}
