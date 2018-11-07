package com.fpt.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.fpt.entity.Payment;
import com.fpt.repository.PaymentRepository;
import com.fpt.service.PaymentService;

public class PaymentServiceImp implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public int checkPayment(String cardNumber, int cvv, String expMonth, int expYear) {
		Payment payment = paymentRepository.getPayment(cardNumber, expMonth, cvv, expYear);
		return payment != null ? payment.getId() : -1;
	}

	@Override
	public boolean checkBalance(int id, double balance) {
		Payment payment = paymentRepository.getOne(id);
		return (payment != null && payment.getBalance() > balance);
	}

}
