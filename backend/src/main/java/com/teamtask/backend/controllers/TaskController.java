package com.teamtask.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtask.backend.dto.TaskRequest;
import com.teamtask.backend.dto.TaskResponse;
import com.teamtask.backend.service.TaskService;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@PostMapping("")
	public ResponseEntity<Map<String, UUID>> createTask(@AuthenticationPrincipal Jwt jwt, @RequestBody TaskRequest request){
		int userId = Integer.valueOf(jwt.getSubject());
		UUID taskId = taskService.create(userId, request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Map.of("taskId", taskId));
	}
	
	@GetMapping("")
	public ResponseEntity<Map<String, List<TaskResponse>>> getUserTasks(@AuthenticationPrincipal Jwt jwt){
		Integer userId = Integer.valueOf(jwt.getSubject());
		List<TaskResponse> tasks = taskService.getTasksByUserId(userId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("tasks", tasks));
	}
	

	@GetMapping("/{taskId}")
	public ResponseEntity<Map<String, TaskResponse>> getUserTaskById(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID taskId){
		int userId = Integer.valueOf(jwt.getSubject());
		TaskResponse task = taskService.getTasksByTaskId(userId, taskId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("task", task));
	}
	
	@PatchMapping("/{taskId}/complete")
	public ResponseEntity<Map<String, TaskResponse>> markUserTaskComplete(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID taskId){
		int userId = Integer.valueOf(jwt.getSubject());
		TaskResponse task = taskService.markTaskAsCompleted(userId, taskId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("task", task));
	}
}
