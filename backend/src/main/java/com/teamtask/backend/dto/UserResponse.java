package com.teamtask.backend.dto;

import com.teamtask.backend.entity.User;

public class UserResponse {
	private int userId;
	private String name;
	private String email;
	
	public UserResponse() {}
	public UserResponse(int userId, String name, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
	}


	public static UserResponse fromUserEntity(User user) {
		return new UserResponse(user.getUserId(), user.getName(), user.getEmail());
		
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
	@Override
	public String toString() {
		return "UserResponse [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
