package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "namesite", columnDefinition = "nvarchar(50)")
	private String nameSite;

	public Site() {
		super();
	}

	public Site(int id, String nameSite) {
		super();
		this.id = id;
		this.nameSite = nameSite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSite() {
		return nameSite;
	}

	public void setNameSite(String nameSite) {
		this.nameSite = nameSite;
	}

}
