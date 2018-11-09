package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.entity.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Integer>{
	@Query("SELECT p FROM Payment p WHERE p.cardNumber = :cardNumber AND p.expMonth = :expMonth AND p.cvv = :cvv AND p.expYear = :expYear")
	Payment getPayment(@Param("cardNumber") String cardNumber, @Param("expMonth") String expMonth, @Param("cvv") int cvv, @Param("expYear") int expYear);
	
	@Query("SELECT p FROM Payment p WHERE p.cardNumber = :cardNumber")
	Payment checkPayment(@Param("cardNumber") String cardNumber);
}
