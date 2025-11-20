package com.teamtask.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamtask.backend.entity.Task;
import com.teamtask.backend.entity.User;

public interface TaskRepository extends JpaRepository<Task, UUID>{
	public List<Task> findByAssignedTo(User user);
}
