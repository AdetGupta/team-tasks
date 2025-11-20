package com.teamtask.backend.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.teamtask.backend.dto.TaskRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID taskId;
	@Column(nullable = false)
	private String title;
	private String description;
	private String priority;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to", nullable = false)
	private User assignedTo;
	private LocalDateTime dueAt;
	private Boolean isCompleted;
	private LocalDateTime createdOn;
	
	public Task() {}
	public Task(String title, String description, String priority, User assignedTo, LocalDateTime dueAt) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.assignedTo = assignedTo;
		this.dueAt = dueAt;
		this.isCompleted = false;
		this.createdOn = LocalDateTime.now();
	}

	public static Task fromRequest(TaskRequest request, User assignedTo) {
		return new Task(request.getTitle(), request.getDescription(), request.getPriority(), assignedTo, request.getDueAt());
	}
	
	public UUID getTaskId() {
		return taskId;
	}
	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
	}
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
	public User getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	public LocalDateTime getDueAt() {
		return dueAt;
	}
	public void setDueAt(LocalDateTime dueAt) {
		this.dueAt = dueAt;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
}
