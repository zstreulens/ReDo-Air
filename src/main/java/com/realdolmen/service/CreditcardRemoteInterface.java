package com.realdolmen.service;

import javax.ejb.Remote;

import com.realdolmen.domain.Creditcard;

@Remote
public interface CreditcardRemoteInterface {
	Creditcard createCreditcard(Creditcard creditcard);

	Creditcard updateCreditcard(Creditcard creditcard);

	Creditcard findById(Integer id);
}
