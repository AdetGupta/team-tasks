package com.teamtask.backend.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teamtask.backend.dto.RequestTaskDto;
import com.teamtask.backend.entity.Task;
import com.teamtask.backend.entity.User;
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

	public UUID create(int userId, RequestTaskDto request) {
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		Task task = Task.fromRequest(request, user);
		user.addTask(task);
		taskRepository.save(task);
		return task.getTaskId();
	}

}
