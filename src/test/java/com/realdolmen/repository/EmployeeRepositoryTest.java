package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Employee;
import com.realdolmen.utilities.JpaPersistenceTest;

public class EmployeeRepositoryTest extends JpaPersistenceTest {
	private EmployeeRepository employeeRepository;
	private Employee employee;
	
	@Before
	public void initializeRepository(){
		employeeRepository = new EmployeeRepository();
		employeeRepository.entityManager = entityManager();
		employee = new Employee();
		employee.setMailAddress("cato@test.be");
		employee.setPassword("test");
	}
	
	@Test
	public void shouldSaveEmployee(){
		employeeRepository.createEmployee(employee);
		assertNotNull(employee.getId());
	}
	
	@Test
	public void shouldFindEmployeeByMail(){
		employeeRepository.createEmployee(employee);
		assertSame(employee, employeeRepository.findByMail(employee.getMailAddress()));
	}
	
	@Test
	public void shouldFindEmployeeById(){
		employeeRepository.createEmployee(employee);
		assertSame(employee, employeeRepository.findById(employee.getId()));
	}
}
