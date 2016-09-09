package com.realdolmen.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Employee;

@Stateless
public class EmployeeRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	public Employee createEmployee(Employee employee){
		entityManager.persist(employee);
		return employee;
	}
	
	public Employee findByMail(String mail){
		return entityManager.createNamedQuery("Employee.findByMail", Employee.class).setParameter("mailAddress", mail).getSingleResult();
	}
	
	public Employee findById(Integer id){
		return entityManager.find(Employee.class, id);
	}
}
