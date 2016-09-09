package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.domain.Employee;
import com.realdolmen.service.EmployeeServiceBean;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
	@Inject
	EmployeeServiceBean employeeServiceBean;
	private Employee employee;
	private String password;
	private Employee loggedInEmployee;
	private String errorMessage;

	@PostConstruct
	public void init() {
		employee = new Employee();
		loggedInEmployee = null;
	}

	public String login() {
		try {
			employee = employeeServiceBean.findByMail(employee.getMailAddress());
			if (password.equals(employee.getPassword())) {
				loggedInEmployee = employee;
				errorMessage = "";
				return "pricing.xhtml";
			} else {
				errorMessage = "Password is incorrect";
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "E-mail is incorrect.";
			return "failure";
		}
	}

	public String logout() {
		cleanEmployee();
		return "employee.xhtml";
	}

	public void cleanEmployee() {
		employee = new Employee();
		loggedInEmployee = null;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Produces
	@Named("employee")
	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	public void setLoggedInEmployee(Employee loggedInEmployee) {
		this.loggedInEmployee = loggedInEmployee;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
