package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Payment getPaymentByCardNumberAndExpMonthAndCvvAndExpYear(String cardNumber, String expMonth, int cvv, int expYear);

	Payment findPaymentByCardNumber(String cardNumber);
}
