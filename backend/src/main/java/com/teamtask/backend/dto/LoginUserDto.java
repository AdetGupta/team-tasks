package com.teamtask.backend.dto;

public class LoginUserDto {
	public String email;
	public String password;
	
	public LoginUserDto() {}

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
	
	
}
