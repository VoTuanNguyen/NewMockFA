package com.fpt.model;

public class AdminChart {
	private int userTotal;
	private int admin;
	private int staff;
	private int user;

	public AdminChart() {
		super();
	}

	public AdminChart(int userTotal, int admin, int staff, int user) {
		super();
		this.userTotal = userTotal;
		this.admin = admin;
		this.staff = staff;
		this.user = user;
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getStaff() {
		return staff;
	}

	public void setStaff(int staff) {
		this.staff = staff;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

}
