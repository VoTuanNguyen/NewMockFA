package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "seattotal", columnDefinition = "int")
	private int seatTotal;
	@Column(name = "status", columnDefinition = "int")
	private int status;
	@Column(name = "cardnumber", unique = true, columnDefinition = "text")
	private String cardNumber;

	public Bus() {
		super();
	}

	public Bus(int id, int seatTotal, int status, String cardNumber) {
		super();
		this.id = id;
		this.seatTotal = seatTotal;
		this.status = status;
		this.cardNumber = cardNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeatTotal() {
		return seatTotal;
	}

	public void setSeatTotal(int seatTotal) {
		this.seatTotal = seatTotal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", seatTotal=" + seatTotal + ", status=" + status + ", cardNumber=" + cardNumber + "]";
	}

}
