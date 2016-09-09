package com.realdolmen.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.realdolmen.domain.Creditcard;
import com.realdolmen.repository.CreditcardRepository;

@Stateless
@LocalBean
public class CreditcardServiceBean implements CreditcardRemoteInterface {
	@Inject
	CreditcardRepository creditcardRepository;
	
	@Override
	public Creditcard createCreditcard(Creditcard creditcard){
		return creditcardRepository.createCreditcard(creditcard);
	}
	
	@Override
	public Creditcard updateCreditcard(Creditcard creditcard){
		return creditcardRepository.updateCreditcard(creditcard);
	}
	
	@Override
	public Creditcard findById(Integer id){
		return creditcardRepository.findById(id);
	}
}
