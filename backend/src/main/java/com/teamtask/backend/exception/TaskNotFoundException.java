package com.teamtask.backend.exception;

public class TaskNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException() {
		super("Task not found");
	}
	
}
