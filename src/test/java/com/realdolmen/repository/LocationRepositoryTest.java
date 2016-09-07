package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Location;
import com.realdolmen.utilities.JpaPersistenceTest;

public class LocationRepositoryTest extends JpaPersistenceTest {
	private LocationRepository locationRepository;
	private Location location;
	
	@Before
	public void initializeRepository(){
		locationRepository = new LocationRepository();
		locationRepository.em = entityManager();
		location = new Location();
		location.setCountry("BelgiumTest");
		location.setCode("Test");
		location.setGlobalRegion("TestEurope");
		location.setName("Testport");
	}
}
