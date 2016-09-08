package com.realdolmen.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Company;

@Stateless
public class CompanyRepository {
	@PersistenceContext
	EntityManager entityManager;

	public Company createCompany(Company company) {
		entityManager.persist(company);
		return company;
	}

	public Company updateCompany(Company company) {
		entityManager.merge(company);
		return company;
	}

	public Company findById(Integer id) {
		return entityManager.find(Company.class, id);
	}

	public Company findByName(String name) {
		try {
			return entityManager.createNamedQuery("Company.findByName", Company.class).setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}
}
