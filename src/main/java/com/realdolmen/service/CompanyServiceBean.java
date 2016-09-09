package com.realdolmen.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Company;
import com.realdolmen.repository.CompanyRepository;

@Stateless
@LocalBean
public class CompanyServiceBean implements CompanyRemoteInterface {

	@Inject
	CompanyRepository companyRepository;

	@Override
	public Company createCompany(Company company) {
		return companyRepository.createCompany(company);
	}

	@Override
	public Company updateCompany(Company company) {
		return companyRepository.updateCompany(company);
	}

	@Override
	public Company findById(Integer id) {
		return companyRepository.findById(id);
	}

	@Override
	public Company findByName(String name) {
		return companyRepository.findByName(name);
	}
}
