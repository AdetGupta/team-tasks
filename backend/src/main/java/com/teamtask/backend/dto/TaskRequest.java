package com.teamtask.backend.dto;

import java.time.LocalDateTime;


public class TaskRequest {
	private String title; 
	private String description;
	private String priority;
	private int assignedTo;
	private LocalDateTime dueAt;
	
	public TaskRequest() {}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public LocalDateTime getDueAt() {
		return dueAt;
	}
	public void setDueAt(LocalDateTime dueAt) {
		this.dueAt = dueAt;
	}
	
	
}
