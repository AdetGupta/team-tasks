package com.teamtask.backend.exception;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedException() {
		super("Unauthorized");
	}

}
