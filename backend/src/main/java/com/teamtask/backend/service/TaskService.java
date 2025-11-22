package com.teamtask.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teamtask.backend.dto.TaskRequest;
import com.teamtask.backend.dto.TaskResponse;
import com.teamtask.backend.entity.Task;
import com.teamtask.backend.entity.User;
import com.teamtask.backend.exception.NotAuthorizedException;
import com.teamtask.backend.exception.TaskNotFoundException;
import com.teamtask.backend.exception.UserNotFoundException;
import com.teamtask.backend.repository.TaskRepository;
import com.teamtask.backend.repository.UserRepository;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	private UserRepository userRepository;
	
	public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
		super();
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	public UUID create(int userId, TaskRequest request) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		Task task = Task.fromRequest(request, user);
		user.addTask(task);
		taskRepository.save(task);
		return task.getTaskId();
	}

	public List<TaskResponse> getTasksByUserId(int userId) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		List<Task> tasks = taskRepository.findByAssignedTo(user);
		return tasks.stream()
	            .map(TaskResponse::fromTaskEntity)
	            .toList();
	}

	public TaskResponse getTasksByTaskId(int userId, UUID taskId) {
		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
		if(task.getAssignedTo().getUserId() != userId) {
			throw new NotAuthorizedException();
		}
		return TaskResponse.fromTaskEntity(task);
	}
	
	public TaskResponse markTaskAsCompleted(int userId, UUID taskId) {
		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
		if(task.getAssignedTo().getUserId() != userId) {
			throw new NotAuthorizedException();
		}
		task.setIsCompleted(true);
		taskRepository.save(task);
		return TaskResponse.fromTaskEntity(task);
	}

	public TaskResponse updateTask(int userId, UUID taskId, TaskRequest request) {
		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
		if(task.getAssignedTo().getUserId() != userId) {
			throw new NotAuthorizedException();
		}
		task.updateFromRequest(request);
		taskRepository.save(task);
		return TaskResponse.fromTaskEntity(task);
	}

	public void deleteTask(int userId, UUID taskId) {
		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
		if(task.getAssignedTo().getUserId() != userId) {
			throw new NotAuthorizedException();
		}
		taskRepository.delete(task);
	}

}
