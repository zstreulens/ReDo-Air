package com.realdolmen.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Employee;
import com.realdolmen.repository.EmployeeRepository;

@Stateless
@LocalBean
public class EmployeeServiceBean implements EmployeeRemoteInterface {
	@Inject
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee createEmployee(Employee employee){
		return employeeRepository.createEmployee(employee);
	}
	
	@Override
	public Employee findByMail(String mail){
		return employeeRepository.findByMail(mail);
	}
	
	@Override
	public Employee findById(Integer id){
		return employeeRepository.findById(id);
	}
}
