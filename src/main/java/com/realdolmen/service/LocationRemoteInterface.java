package com.realdolmen.service;

import java.util.List;

import javax.ejb.Remote;

import com.realdolmen.domain.Location;
@Remote
public interface LocationRemoteInterface {
	List<Location> findLocations();
	List<String> findCountries();
	Location createLocation(Location flight);
	Location findLocationByAirport(String airport);
	List<String> findAirports();
	List<String> findAirportsByCountry(String country);
}
