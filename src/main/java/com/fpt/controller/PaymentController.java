package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Payment;
import com.fpt.model.Message;
import com.fpt.service.PaymentService;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	//check exist credit card to payment
	@PostMapping("/checkpayment")
	public Message checkPayment(@RequestBody Payment payment) {
		int rs = paymentService.checkPayment(payment.getCardNumber(), payment.getCvv(), payment.getExpMonth(), payment.getExpYear());
		return rs != -1 ? new Message(String.valueOf(rs)) : new Message("KO");
	}
	//get account payment from card number
	@PostMapping("/checkpaymentcardnumber")
	public Message checkPaymentFromCardNumber(@RequestBody String cardNumber) {
		Payment payment = paymentService.checkPayment(cardNumber);
		return new Message(payment != null ? String.valueOf(payment.getId()) : "KO");
	}
}
