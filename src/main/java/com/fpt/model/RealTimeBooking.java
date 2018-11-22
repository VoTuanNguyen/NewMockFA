package com.fpt.model;

public class RealTimeBooking {
	private int trip_id;
	private String date;
	private int bus_id;

	public RealTimeBooking() {
		super();
	}

	public RealTimeBooking(int trip_id, String date, int bus_id) {
		super();
		this.trip_id = trip_id;
		this.date = date;
		this.bus_id = bus_id;
	}

	public int getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

}
