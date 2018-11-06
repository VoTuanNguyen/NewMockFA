package com.fpt.model;

import com.fpt.entity.User;

public class SaveBooking {
	private User user;
	private String[] lstSeat;

	public SaveBooking() {
		super();
	}

	public SaveBooking(User user, String[] lstSeat) {
		super();
		this.user = user;
		this.lstSeat = lstSeat;
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

}
