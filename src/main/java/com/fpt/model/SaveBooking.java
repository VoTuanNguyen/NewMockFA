package com.fpt.model;

import com.fpt.entity.Payment;
import com.fpt.entity.User;

public class SaveBooking {
	private User user;
	private String[] lstSeat;
	private Payment payment;

	public SaveBooking() {
		super();
	}

	public SaveBooking(User user, String[] lstSeat, Payment payment) {
		super();
		this.user = user;
		this.lstSeat = lstSeat;
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getLstSeat() {
		return lstSeat;
	}

	public void setLstSeat(String[] lstSeat) {
		this.lstSeat = lstSeat;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
