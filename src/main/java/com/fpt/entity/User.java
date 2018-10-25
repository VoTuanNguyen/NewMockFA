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
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", columnDefinition = "nvarchar(50)")
	private String name;
	@Column(name = "email", unique = true, columnDefinition = "varchar(50)")
	private String email;
	@Column(name = "username", unique = true, columnDefinition = "varchar(50)")
	private String username;
	@Column(name = "password", columnDefinition = "text")
	private String password;
	@Column(name = "phone", columnDefinition = "text")
	private String phone;
	@Column(name = "address", columnDefinition = "text")
	private String address;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	@Column(name = "creator", columnDefinition = "int(2)")
	private int creator; // 1 - creator: administrator 0 - creator: user

	public User() {
		super();
	}

	public User(int id, String name, String email, String username, String password, String phone, String address,
			Role role, int creator) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.creator = creator;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", address=" + address + ", role=" + role + ", creator=" + creator
				+ "]";
	}

}
