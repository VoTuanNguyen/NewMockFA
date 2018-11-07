package com.fpt.service;

public interface PaymentService {
	int checkPayment(String cardNumber, int cvv, String expMonth, int expYear);
	boolean checkBalance(int id, double balance);
}
