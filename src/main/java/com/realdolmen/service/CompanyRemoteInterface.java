package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.Company;

@Remote
public interface CompanyRemoteInterface {
	Company createCompany(Company company);

	Company updateCompany(Company company);

	Company findById(Integer id);

	Company findByName(String name);
}