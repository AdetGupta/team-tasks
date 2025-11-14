package com.teamtask.backend.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtask.backend.dto.RequestTaskDto;
import com.teamtask.backend.service.TaskService;

@RestController
@RequestMapping("tasks")
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@PostMapping("/me")
	public ResponseEntity<Map<String, UUID>> createTask(@AuthenticationPrincipal Jwt jwt, @RequestBody RequestTaskDto request){
		int userId = Integer.valueOf(jwt.getSubject());
		UUID taskId = taskService.create(userId, request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Map.of("taskId", taskId));
	}
}
