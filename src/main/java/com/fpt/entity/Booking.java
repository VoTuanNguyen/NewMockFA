package com.fpt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", columnDefinition = "nvarchar(50)")
	private String name;
	@Column(name = "email", columnDefinition = "varchar(50)")
	private String email;
	@Column(name = "phone", columnDefinition = "varchar(20)")
	private String phone;
	@Column(name = "address", columnDefinition = "text")
	private String address;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	@ManyToOne
	@JoinColumn(name = "trip_id", nullable = false)
	private Trip trip;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "status")
	private int status;
	@Column(name = "seatnumber")
	private String seatNumber;

	public Booking() {
		super();
	}

	public Booking(int id, String name, String email, String phone, String address, User user, Trip trip, Date date,
			int status, String seatNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.user = user;
		this.trip = trip;
		this.date = date;
		this.status = status;
		this.seatNumber = seatNumber;
	}

	public Booking(String name, String email, String phone, String address, User user, Trip trip, Date date, int status,
			String seatNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.user = user;
		this.trip = trip;
		this.date = date;
		this.status = status;
		this.seatNumber = seatNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "Booking [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", user=" + user + ", trip=" + trip + ", date=" + date + ", status=" + status + ", seatNumber="
				+ seatNumber + "]";
	}

}
