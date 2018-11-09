package com.fpt.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Payment;
import com.fpt.repository.PaymentRepository;
import com.fpt.service.PaymentService;

@Service
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

	@Override
	public boolean updateBalance(int id, double balance) {
		Payment payment = paymentRepository.getOne(id);
		payment.setBalance(payment.getBalance() - balance);
		
		return paymentRepository.saveAndFlush(payment) != null;
	}

	@Override
	public Payment checkPayment(String cardNumber) {
		return paymentRepository.checkPayment(cardNumber);
	}

	@Override
	public boolean updateBalance(String cardNumber, double balance) {
		Payment p = paymentRepository.checkPayment(cardNumber);
		p.setBalance(p.getBalance() + balance);
		return paymentRepository.saveAndFlush(p) != null;
	}

}
