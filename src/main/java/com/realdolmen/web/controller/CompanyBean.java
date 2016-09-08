package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.BCrypt;

import com.realdolmen.domain.Company;
import com.realdolmen.repository.CompanyRepository;
import com.realdolmen.repository.CustomerRepository;

@Named
@SessionScoped
public class CompanyBean implements Serializable {
	@Inject
	CompanyRepository companyRepository;
	private Company company;
	private String password;
	private Company loggedInCompany;
	String errorMessage;

	@PostConstruct
	public void init() {
		company = new Company();
		loggedInCompany = null;
	}

	public String register() {
		try {
			company.setPassword(password);
			companyRepository.createCompany(company);
			cleanCompany();
			return "registered";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	public String login() {
		try {
			company = companyRepository.findByName(company.getName());
			if (BCrypt.checkpw(password, company.getPassword())) {
				loggedInCompany = company;
				errorMessage = "";
				return "loggedIn";
			} else {
				errorMessage = "Password is incorrect.";
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Name is incorrect.";
			return "failure";
		}
	}

	public String logout() {
		cleanCompany();
		return "company.xhtml";
	}

	public void cleanCompany() {
		company = new Company();
		loggedInCompany = null;
		password = null;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Produces
	@Named("company")
	public Company getLoggedInCompany() {
		return loggedInCompany;
	}

	public void setLoggedInCompany(Company loggedInCompany) {
		this.loggedInCompany = loggedInCompany;
	}

}
