package com.realdolmen.web.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private boolean creditcardPayment = true;

	public boolean isCreditcardPayment() {
		return creditcardPayment;
	}

	public void setCreditcardPayment(boolean creditcardPayment) {
		this.creditcardPayment = creditcardPayment;
	}

}