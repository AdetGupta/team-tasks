package com.teamtask.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamtask.backend.entity.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{

}
