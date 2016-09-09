package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.Employee;

@Remote
public interface EmployeeRemoteInterface {
	Employee createEmployee(Employee employee);

	Employee findByMail(String mail);

	Employee findById(Integer id);
}
