package com.test.taskmanagement.repository;

import org.springframework.stereotype.Repository;

import com.test.taskmanagement.entity.Task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>{
    
}
