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
@Table(name = "trip")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "time", columnDefinition = "text")
	private String time;
	@ManyToOne
	@JoinColumn(name = "bus_id", nullable = false)
	private Bus bus;
	@ManyToOne
	@JoinColumn(name = "route_id", nullable = false)
	private Route route;

	public Trip() {
		super();
	}

	public Trip(int id, String time, Bus bus, Route route) {
		super();
		this.id = id;
		this.time = time;
		this.bus = bus;
		this.route = route;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", time=" + time + ", bus=" + bus + ", route=" + route + "]";
	}

}
