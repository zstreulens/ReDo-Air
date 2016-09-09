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
import com.realdolmen.service.CompanyServiceBean;

@Named
@SessionScoped
public class CompanyBean implements Serializable {
	@Inject
	CompanyServiceBean companyService;
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
		if (companyService.findByName(company.getName()) == null) {
			try {
				company.setPassword(password);
				companyService.createCompany(company);
				cleanCompany();
				return "registered";
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Something went wrong.";
				return "failure";
			}
		} else {
			errorMessage = "Company already exists.";
			return "failure";
		}
	}

	public String login() {
		try {
			company = companyService.findByName(company.getName());
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
