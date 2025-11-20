package com.teamtask.backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.teamtask.backend.dto.RegisterUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	private Integer userId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private LocalDate createdOn;
	@OneToMany(
            mappedBy = "assignedTo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
	private List<Task> tasks;
	
	
	public User() {}
	public User(RegisterUser request) {
		this.name = request.name;
		this.email = request.email;
		this.password = request.password;
		this.createdOn = LocalDate.now();
		this.setTasks(new ArrayList<Task>());
		
	}
	
	public void addTask(Task task) {
		try {
			task.setAssignedTo(this);
			tasks.add(task);
		} catch (Exception e) {
			throw e;
		}
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
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createdOn=" + createdOn + ", tasks=" + tasks + "]";
	}
	
	
}
