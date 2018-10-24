package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "status", columnDefinition = "int")
	private int status;
	@Column(name = "seatnumber", columnDefinition = "text")
	private String seatNumber;
	@ManyToOne
	@JoinColumn(name = "bus_id", nullable = false)
	private Bus bus;

	public Seat() {
		super();
	}

	public Seat(int id, int status, String seatNumber, Bus bus) {
		super();
		this.id = id;
		this.status = status;
		this.seatNumber = seatNumber;
		this.bus = bus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", status=" + status + ", seatNumber=" + seatNumber + ", bus=" + bus + "]";
	}

}
