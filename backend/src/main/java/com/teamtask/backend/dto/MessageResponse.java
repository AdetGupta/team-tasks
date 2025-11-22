package com.teamtask.backend.dto;

public class MessageResponse {
	private String message;
	private Object data;
	
	public MessageResponse(String message, Object object) {
		this.message = message;
		this.data = object; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
