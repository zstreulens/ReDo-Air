package com.realdolmen.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.domain.Creditcard;

@Stateless
public class CreditcardRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	public Creditcard createCreditcard(Creditcard creditcard){
		entityManager.merge(creditcard);
		return creditcard;
	}
	
	public Creditcard updateCreditcard(Creditcard creditcard){
		entityManager.merge(creditcard);
		return creditcard;
	}
	
	public Creditcard findById(Integer id){
		return entityManager.find(Creditcard.class, id);
	}
}
