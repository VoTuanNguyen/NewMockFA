package com.fpt.service;

import com.fpt.entity.Payment;

public interface PaymentService {
	int checkPayment(String cardNumber, int cvv, String expMonth, int expYear);
	boolean checkBalance(int id, double balance);
	boolean updateBalance(int id, double balance);
	boolean updateBalance(String cardNumber, double balance);
	Payment checkPayment(String cardNumber);
}
