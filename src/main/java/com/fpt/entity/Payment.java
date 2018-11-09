package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(name = "cardnumber", columnDefinition = "varchar(20)", unique = true)
	private String cardNumber;
	
	@Column(name = "expmonth", columnDefinition = "varchar(10)")
	private String expMonth;
	
	@Column(name = "expyear", columnDefinition = "int")
	private int expYear;
	
	@Column(name = "cvv", columnDefinition = "int")
	private int cvv;
	
	@Column(name = "balance", columnDefinition = "double")
	private double balance;

	public Payment() {
		super();
	}

	public Payment(int id, String name, String cardNumber, String expMonth, int expYear, int cvv, double balance) {
		super();
		this.id = id;
		this.name = name;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvv = cvv;
		this.balance = balance;
	}

	public Payment(String name, String cardNumber, String expMonth, int expYear, int cvv, double balance) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvv = cvv;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
