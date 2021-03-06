package com.realdolmen.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Location;
import com.realdolmen.repository.LocationRepository;
@Stateless
@LocalBean
public class LocationServiceBean implements LocationRemoteInterface{

	@Inject
	LocationRepository locationRepository;
	
	@Override
	public List<Location> findLocations() {
		return locationRepository.findAll();
	}

	@Override
	public Location createLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public List<String> findCountries() {
		return locationRepository.findCountries();
	}
	
	@Override
	public Location findLocationByAirport(String airport){
		return locationRepository.findByAirport(airport);
	}
	
	@Override
	public List<String> findAirports(){
		return locationRepository.findAirports();
	}
	
	@Override
	public List<String> findAirportsByCountry(String country){
		return locationRepository.findAirportsByCountry(country);
	}

}
