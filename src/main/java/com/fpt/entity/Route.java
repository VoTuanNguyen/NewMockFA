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
@Table(name = "route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "src", nullable = false)
	private Site src;
	@ManyToOne
	@JoinColumn(name = "dest", nullable = false)
	private Site dest;
	@Column(name = "price", columnDefinition = "double")
	private double price;

	public Route() {
		super();
	}

	public Route(int id, Site src, Site dest, double price) {
		super();
		this.id = id;
		this.src = src;
		this.dest = dest;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Site getSrc() {
		return src;
	}

	public void setSrc(Site src) {
		this.src = src;
	}

	public Site getDest() {
		return dest;
	}

	public void setDest(Site dest) {
		this.dest = dest;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", src=" + src + ", dest=" + dest + ", price=" + price + "]";
	}

}
