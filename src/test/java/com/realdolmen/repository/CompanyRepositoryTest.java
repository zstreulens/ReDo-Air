package com.realdolmen.repository;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.domain.Company;
import com.realdolmen.utilities.JpaPersistenceTest;

public class CompanyRepositoryTest extends JpaPersistenceTest {
	private CompanyRepository companyRepository;
	private Company company;
	
	@Before
	public void initializeRepository(){
		companyRepository = new CompanyRepository();
		companyRepository.entityManager = entityManager();
		company = new Company();
		company.setName("Brussels Airlines");
		company.setPassword("test");
	}
	
	@Test
	public void shouldSaveCompany(){
		companyRepository.createCompany(company);
		assertNotNull(company.getId());
	}
	
	@Test
	public void shouldUpdateCompany(){
		companyRepository.createCompany(company);
		company.setName("Ryanair");
		companyRepository.updateCompany(company);
		Company foundCompany = companyRepository.findById(company.getId());
		assertEquals("Ryanair", foundCompany.getName());
	}
	
	@Test
	public void shouldFindCompanyById(){
		companyRepository.createCompany(company);
		assertSame(company, companyRepository.findById(company.getId()));
	}
	
	@Test
	public void shouldFindCompanyByName(){
		companyRepository.createCompany(company);
		assertSame(company, companyRepository.findByName(company.getName()));
	}
}
