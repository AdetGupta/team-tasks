package com.teamtask.backend.entity;

import java.time.LocalDate;

import com.teamtask.backend.dto.RegisterUserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	public int userId;
	@Column(nullable = false)
	public String name;
	@Column(nullable = false, unique = true)
	public String email;
	@Column(nullable = false)
	public String password;
	public LocalDate createdOn;
	
	public User() {}
	public User(RegisterUserDto request) {
		this.name = request.name;
		this.email = request.email;
		this.password = request.password;
		this.createdOn = LocalDate.now();
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
