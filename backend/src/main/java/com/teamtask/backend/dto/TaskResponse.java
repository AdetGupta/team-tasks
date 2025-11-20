package com.teamtask.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.teamtask.backend.entity.Task;

public class TaskResponse {
	private UUID id;
	private String title;
	private String description;
	private String priority;
	private UserResponse assignedTo;
	private Boolean isCompleted;
	private LocalDateTime dueAt;
	
	public TaskResponse() {};
	public TaskResponse(UUID id, String title, String description, String priority, UserResponse assignedTo,
			Boolean isCompleted, LocalDateTime dueAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.assignedTo = assignedTo;
		this.isCompleted = isCompleted;
		this.dueAt = dueAt;
	}


	public static TaskResponse fromTaskEntity(Task task) {
		TaskResponse response = new TaskResponse(
					task.getTaskId(),
					task.getTitle(),
					task.getDescription(),
					task.getPriority(),
					UserResponse.fromUserEntity(task.getAssignedTo()),
					task.getIsCompleted(),
					task.getDueAt()
				);
		return response;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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

	public UserResponse getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserResponse assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDateTime getDueAt() {
		return dueAt;
	}

	public void setDueAt(LocalDateTime dueAt) {
		this.dueAt = dueAt;
	}
	
	
}
